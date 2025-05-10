package org.nestharus.parser.node;

import com.google.common.collect.Range;
import lombok.Builder;
import org.jspecify.annotations.NonNull;

@Builder
public record WildcardInterval(Range<@NonNull Integer> interval, WildcardIntervalType type) {}
