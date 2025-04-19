## 1. Syntax Features
### 1.1 Basic Structure
- Routes consist of segments separated by the `/` character.
- Patterns define the structure that incoming URL paths must match.
- Example: `/users/profile/settings`

### 1.2 Static Segments
- Match exact literal strings in a URL segment.
- If a static segment consists only of optional text (e.g., defined as `b?` in the grammar) and that text is absent in the URL path, the entire segment is effectively skipped during matching, joining the preceding and succeeding parts. It does **not** result in an empty segment like `//` (which is generally invalid).
- Syntax: `literal`
- Example: If a route is defined conceptually like `/a/b?/c` (where `b?` represents an optional static segment 'b'):
  - It matches `/a/b/c`.
  - It matches `/a/c` (the optional `b` segment is skipped).
  - It does **not** match `/a//c`.

### 1.3 Optional Segments (`/?`)
- The syntax `/?<pattern>` makes the _entire segment_ defined by `<pattern>` optional.
- If the optional segment exists in the URL and matches `<pattern>`, its parameters are captured. If the segment is absent, matching continues with the next part of the route definition.
- This is distinct from making content _within_ a segment optional (e.g., `/a/b?/c` vs `/a/?b/c`).
- When an optional segment containing captures is matched, the captured values might be wrapped in a structure (like an `Optional` or a dedicated object) to distinguish presence from absence, especially if named.
- Syntax: `/?<pattern>`
- Example: `/users/?(profile|settings)/details`
    - Matches `/users/profile/details` (capturing `profile`)
    - Matches `/users/settings/details` (capturing `settings`)
    - Matches `/users/details` (no capture for the optional segment)

- Example: `/files/?*<filename>`
    - Matches `/files/report.txt` (results in an object/structure like `{ filename: "report.txt" }` or Optional["report.txt"])
    - Matches `/files/` (results in an empty optional / null structure)

### 1.4 Wildcard Segments (`*`)
- Conservatively match any single URL segment that is not empty. The match consumes the minimum necessary for the overall route pattern to succeed.
- Can capture the matched segment's value into a named parameter.
- The _capture_ can be marked optional with `?` _after_ the name (`*<name>?`). This does _not_ make the segment optional (use `/?*...` for that). If the segment exists and is matched by `*`, `name` gets the value. If the segment doesn't exist (because it was optional via `/?`), `name` might be null/absent.
- Can be constrained by static prefixes or suffixes _within the segment_.
- Syntax: `prefix*<name>?suffix` (prefix/suffix are static text within the segment, `?` applies to capture)
    - `*`: Unnamed, required single segment.
    - `*<name>`: Required single segment, captured as `name`.
    - `/*<name>?`: Required segment `/*`, capture into `name` is optional (less common, usually used if the `*` itself might match something uncapturable).
    - `/?*<name>`: Optional segment `/?*`. If present and matches, captured as `name`.
    - `prefix*`: Segment must start with `prefix`.
    - `*suffix`: Segment must end with `suffix`.

- Example: `/data/file-*<id>.dat` (Matches `/data/file-123.dat`, `id`="123")
- Example: `/items/?*<itemId>?` (Optional segment `/?*`. If present, capture as `itemId`. The final `?` on `<itemId>?` is likely redundant here, as the segment's optionality handles absence).

### 1.5 Glob Segments (`**`)
- Conservatively match one or more URL segments. The match consumes the minimum number of segments necessary for the overall route pattern to succeed.
- Can be entirely optional (match zero segments) using the `/?**...` syntax or group optionality like `(...)**?`.
- Can capture the matched segments into a named list parameter.
- Can be preceded by optional static text _within the same segment definition_ based on the grammar (`glob_segment: STATIC_TEXT? GLOB ...`).
- Syntax: `staticPrefix?**<name[quantifier]>?`
    - `/prefix**`: Matches segment `/prefix/` followed by 1+ segments matched by `**`.
    - `/prefix**<name>`: Matches `/prefix/` followed by 1+ segments captured as list `name`.
    - `/?**<name[]>`: Optional glob. Matches 0 or more segments, captured as list `name`. (Using `[]` for 0+ and `/?` for optionality).
    - `/static**<name>`: Matches segment `/static/` followed by 1+ segments matched by `**<name>`. If the grammar allows `static**` as a single segment: matches a segment _starting with_ `static`, followed by 1+ segments matched by `**<name>`. _Assuming standard segmentation:_ `/static/` is one segment, `**<name>` matches the next 1+ segments.

- Example: `/files/**<path>` (Matches `/files/a/b/c`, `path`=["a","b","c"])
- Example: `/archive/?**<files[]>` (Matches `/archive/` (`files`=[]) or `/archive/doc/report.txt` (`files`=["doc", "report.txt"]))

### 1.6 Grouping (`(...)`)
- Groups parts of a pattern using parentheses `()`.
- Used for applying alternatives (`|`) or quantified repetition (`**<>`).
- Can define optional content _within_ a segment: `/segment/(optional)?/part`.
- Example: `/data/(images|documents)/view`

### 1.7 Alternatives (`|`)
- Allows matching one of several alternative patterns within a group `(...)`.
- Separated by the `|` character.
- The group can be named (`(...)<name>`) to capture the result. The captured value should indicate which alternative matched and provide its specific captured parameters (e.g., using a `Pair<Enum, Object>`).
- Syntax: `(<pattern1> | <pattern2>)<name>?`
- Example: `(/user/*<id> | /guest/*<sid>)<session>` (Matches either pattern, captures result as `session` indicating branch and ID)

### 1.8 Quantified Repetition (`**<[quantifier]>`)
- Applies primarily to the preceding **group** `(...)`. It cannot apply to `**` itself (`****` is invalid).
- Specifies the number of times the preceding group must repeat.
- The quantifier `[quantifier]` is optional. If omitted, `(<group>)**` defaults to requiring 1 or more repetitions (`[1,]`).
- Can capture the sequence of matched repetitions into a named list parameter (`(...)**<name[quantifier]>`).
- A trailing `?` _after_ the quantifier (`(...)**<...>?`) makes the entire quantified block optional (it can match zero times, regardless of the minimum specified in the quantifier).
- Syntax: `(<group>) ** <name? [quantifier]? > ?`
    - `(<group>)**`: Requires 1 or more repetitions (`[1,]`).
    - `(<group>)**?`: Requires 0 or more repetitions (`[0,]`). Equivalent to `(<group>)**<[]>`.
    - `(<group>)**<[min,max]>`: Requires `min` to `max` repetitions.
    - `(<group>)**<name[min,]>`: Requires at least `min` repetitions, captured as list `name`.
    - `(<group>)**<name[]>?`: Optionally matches 0 or more repetitions, captured as list `name` if present.

- Examples:
    - `(/segment)**`: Matches 1 or more `/segment`.
    - `(/key/*<k>/value/*<v>)**<pairs[1,]>`: Matches 1+ key/value pairs, captured as list . `pairs`
    - `(/item/*<i>)**<items[]>?`: Optionally matches 0+ `/item/*` segments, capturing results as list `items`.
    - `(/block)**<blocks[2,]>?:` Matches 0 repetitions OR 2+ repetitions, captured as `blocks` if 2+ occur.

### 1.9 Parameter Capturing Summary
- `*<name>`: Captures a single segment value (e.g., String).
- `**<name>`: Captures multiple segments (e.g., List ). 
- `/?pattern<name>`: If optional segment matches, captures `name` potentially wrapped (e.g., `Optional<String>`, `Optional<List<String>>`).
- `(...)**<name[...]>`: Captures a list where each element represents the captures from one repetition (e.g., List<Map<String, Object>>).
- `(... | ...)<name>`: Captures result indicating matched branch and its data (e.g., `Pair<Enum, Object>`).

## 2. Cookbook: Routing Recipes (Revised)
### 2.1 Basic Routes
- **Static path:** `/admin/settings`
- **Single required parameter:** `/users/*<userId>` (Matches `/users/123`)
- **Optional segment with parameter:** `/search/?*<query>`
    - Matches `/search/widgets` (`query` = "widgets", possibly wrapped)
    - Matches `/search/` (`query` = absent/null/empty Optional)

### 2.2 Wildcards with Affixes
- **Parameter with prefix/suffix:** `/images/thumb-*<id>.png` (Matches `/images/thumb-abc.png`, `id`="abc")

### 2.3 Globs for Multiple Segments
- **Catch-all (1+ segments):** `/public/**`
- **Named glob (1+ segments):** `/documents/**<filePath>` (Matches `/documents/a/b`, `filePath`=["a","b"])
- **Optional Glob (0+ segments):** `/api/?**<endpointPath[]>`
    - Matches `/api/users/list` (`endpointPath` = ["users", "list"])
    - Matches `/api/` (`endpointPath` = [])

### 2.4 Repeating Patterns
- **Required repeating pairs (1+):** `/config(/key/*<k>/value/*<v>)**<settings>` (Default quantifier is `[1,]`)
    - Matches `/config/key/a/value/1/key/b/value/2` (`settings` = list `[{k:"a", v:"1"}, {k:"b", v:"2"}]`)

- **Optional repeating pairs (0+):** `/query(/param/*<n>/val/*<v>)**<params[]>?`
    - Matches `/query` (`params` = [])
    - Matches `/query/param/s/val/asc` (`params` = list `[{n:"s", v:"asc"}]`)

### 2.5 Alternatives
- **Different ID types:** `(/product/*<id> | /item/*<sku>)<itemIdentifier>`
    - Matches `/product/123` (`itemIdentifier` indicates PRODUCT, `id`="123")
    - Matches `/item/XYZ` (`itemIdentifier` indicates ITEM, `sku`="XYZ")

- **Optional command:** `/users/*<uid>/?(edit|view)`
    - Matches `/users/45/edit`
    - Matches `/users/45/view`
    - Matches `/users/45`

### 2.6 Complex Combinations
- **API with optional filters (using optional segments):** `/api/v1/orders/?status/*<s >/?user/*<u>`
    - Matches `/api/v1/orders`
    - Matches `/api/v1/orders/status/pending` (`s`="pending")
    - Matches `/api/v1/orders/user/123` (`u`="123")
    - Matches `/api/v1/orders/status/done/user/456` (`s`="done", `u`="456")

- **Nested structure with optional repetition:** `/data/?(typeA(/id/*<i>)**<setA> | typeB/*<b>)**<dataSet[]>?`
    - Optionally matches 0+ data entries (`dataSet[]?`).
    - Each entry can be `typeA` (with 1+ id segments captured in `setA`) or `typeB` (single segment `b`).
    - The outer `dataSet` list would contain `Pair<Enum, Object>` indicating which type (A or B) matched for that repetition and the corresponding data (`setA` list or `b` value).
