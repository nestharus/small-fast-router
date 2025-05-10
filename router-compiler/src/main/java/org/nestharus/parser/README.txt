1. lexer
2. parser
3. ast
4. capture analysis (duplicate captures)
5. quantifier analysis (bad quantifiers)
6. kleene star algebra
7. kleene star -> dfa storing set expected elements on each dfa node
8. minimize dfas to dawgs merging expected element sets
9. search through dfa looking at 0 and 1 cycle situations for empty segments //
10. search through dfa looking for complete paths with exactly one cycle in it
    that contains a /
11. for all found segments with 1 cycle with a / reverse nodes from the cycle on
    and link transitions pointing to / to the back of the reversed nodes throwing
    all handlers from back nodes into the cycle and marking each node as reversed
    **YOU WILL BE MOVING FORWARDS AND BACKWARDS**
    **THIS REVERSAL WILL CAUSE A SPLIT**
    The reason for the split is because reversals will iterate input backwards
    and regulars will iterate input forwards
    they cannot be merged
    you need to do this on a dawg to correctly optimize all paths that can be optimzed
    identifying children of a cycle and splitting cycle will be difficult
12. serialize to memory segment

kleene star is unnecessary? just go straight to dfa as it'll maximally compact it

label:
    if (true) break my_goto;

only the final transition will have a handler
the handler in the final state will be the handler with the best score

handler score is sorted by
    length of static characters (ALL including patterns)
    length of dynamic characters that do not produce new segments
    length of dynamic characters that can produce new segments
matcher
    match first dfa
    switch (dfa type)
        case handler pointer:
            if remaining input
                bad match
        case dfa pointer:
            match on dfa (happens up to 1 time)
            jump to back of input and match in reverse
            if dfa is handler
                remaining input is **
            else
                bad match
    handlers[handlerId](parameters) -> handler(parameters) { function((T1) parameters[0], (T2) parameters[1]) }

<name> -> by parameter type
<name:TYPE>

a<name:TYPE1>|b<name:TYPE2>|c<name:TYPE3>

**<name> -> String
*<name> -> String
(aa)**<name> -> Object
(aa<name>)**<name:TYPE> -> List<TYPE>

interface Type {
    ENUM getType();
}
class Type1 implements Type {
    ENUM getType() {
        return ENUM.TYPE1;
    }
}

parameters collected -> handlers[i](parameters) -> restHandler((T1) parameter[0], (T2) parameter[1])

Match a Dfa. Get to a final transition. The final transition references another Dfa.
Start matching on the referenced Dfa. Each Dfa has its own handler.
The transition to another Dfa will only happen up to one time per match.
The check will only happen after matching is complete for first Dfa, not on every cycle.
You do not need to push on to the callstack as this will only happen once. Start matching
on first Dfa from front of string.
If you have a second Dfa then start matching on that next from the back of the string.

Don't worry about simplifying.
Translate Ast to Kleene Star Algebraic Expression WITH intersections
Simplify Kleene Star Algebraic Expression
Translate Kleene Star Algebraic Expression to NFA
Translate NFA to DFA (standard).
Minimize DFA to DAWG.
Handle Dfa ** case somewhere?

Minimize
Split by first **
If second element of split is empty then only one Dfa where remainder of input is **.

Recipes
(aa)** -> /?aa(/aa)*?
/(aa)** -> /aa(/aa)*?
b(aa)** -> b/?aa(/aa)*?
/(aa)** -> (/aa)*
any number of (aa) in segments -> /aa(/?aa)*?

So /(aa)** translates to (/?aa)*

1. explode expression with cardinality
-> * to all variants
can tag each individual element of the expression based on captures
and then during collapse can union tags so that as things are matched
they are accumulated to a particular tag

(ab(c|d)e)|(ab(d|f)*[1,2]e)

ab(c|d)e ->
            a[1](b[1](c[1](e[1])))
            a[1](b[1](d[1](e[1])))

ab(d|f)*[1,2]g ->
            a[1](b[1](d[1,2](g[1])))
            a[1](b[1](f[1,2](g[1])))

aggregate from outside in

a[1](b[1](c[1](e[1])))
a[1](b[1](d[1](e[1])))
a[1](b[1](d[1,2](g[1])))
a[1](b[1](f[1,2](g[1])))

turns into

a[1](
b[1](c[1](e[1]))
b[1](d[1](e[1]))
b[1](d[1,2](g[1]))
b[1](f[1,2](g[1]))
)

turns into

a[1](b[1](
c[1](e[1])
d[1](e[1])
d[1,2](g[1])
f[1,2](g[1])
))

d[1] is subset of d[1,2] because [1] is in [1,2]

d[1](e[1])
d[1](g[1])
d[2](g[1])
f[1,2](g[1])

becomes

a[1](b[1](
    d[1](
        e[1]
        g[1]
    )
    d[2](
        g[1]
    )
    f[1,2](
        g[1]
    )
))

turns into

ab(d(e|g)|(d)*[2]g|(f)*[1,2]g)

Compare
            a[1](b[1](c[1](e[1])))
            a[1](b[1](d[1](e[1])))

            a[1](b[1](d[1,2](g[1])))
            a[1](b[1](f[1,2](g[1])))

Reduce separately

ab(c|d)e
ab(d|f)*[1,2]g

They are not equal

Compare chunks of text

(a)*(a)*

They are equal.
(a)* -> a[1,]
(a)* -> a[1,]

Collapse

a[2,] -> (a)*[2,]

(a)*/(a)*

Not equal
(a)*/

Not equal
/(a)*

Collapse by row and then collapse by column.
(a|b)*abba(b)*

(a)**/(a)**

Expand

a[1]
(/[1]a[1])[0,]
/[1]
a[1]
(/[1]a[1])[0,]

The key point is that the ( ) are like black boxes. Their internals can be modified but they can only work with other units of the exact same type, meaning that they should look the same. We can never split a ( ) but we can certainly combine things to match. So we need combines as well.

So we first look at

a[1]
(/[1]a[1])[0,]

a[1] is the primary. It has nothing it can combine with to match ( ).

Next
(/[1]a[1])[0,]
/[1]

We can only combine our primary if it is less than the next element. ( ) has a depth of 1. Next element has a depth of 0. No combine.

/[1] a[1]

They are the same depth at 0. They don't appear to match. The depth is the same so we cannot combine.

a[1]
(/[1]a[1])[0,]

a[1] depth < than next so don't even try to compare but we can combine.

(/[1]a[1])[1]
(/[1]a[1])[0,]

The internal trees match exactly so can combine. If they did not match then the
outer cardinalities would need to be broken up by overlap and then overlap combined.

Now we can combine

(/[1]a[1])[1,]

Giving us

a[1]
(/[1]a[1])[0,]
(/[1]a[1])[1,]

(/[1]a[1])[0,] is a subset of (/[1]a[1])[1,] so combine

a[1](/[1]a[1])[1,]

Recall that (a)** == a[1](/[1]a[1])[0,]

So a[1](/[1]a[1])[1,] adds 1, turning into (a)**[2,]


Exploding **

(PATTERN)**[X,Y]

Where X == Y == 1
PATTERN

Where X > 0
PATTERN (/PATTERN)[X-1,Y-1]

Where X == 0
(/PATTERN)[,Y]



When collapsing named tags elements NEVER go to all tags. They are allocated sequentially
to the tags. First element goes to tag1. Second element goes to tag2. Etc until tags exhausted.
Once tags are exhausted the remaining elements go into the last tag.

Simplify (aa)**[3,5]/(aa)**[6,9]
/?aa(/aa)[2](/aa)[0,2](/aa)[6](/aa)[0,3]
/[0,1]aa[1](/[1]aa[1])[2](/[1]aa[1])[0,2](/[1]aa[1])[6](/[1]aa[1])[0,3]

/[0,1]
aa[1]
(/[1]aa[1])[2]
(/[1]aa[1])[0,2]
(/[1]aa[1])[6]
(/[1]aa[1])[0,3]

Simplify /aa(/?aa)*?/aa(/?aa)*?


(aa)** -> /?aa(/aa)*?
any number of (aa) in segments -> (/?aa)*