package sample;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Either;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataService {

  public Set<DataRecord> fetchData(Set<Rule> rules, Result result) {
    Map<Criterion, List<Precedence>> criterionPrecedenceMap = fetchCriterionPrecedenceMap(rules);
    Map<String, DataRecord> recordMap = result.getDataRecords()
        .stream()
        .collect(Collectors.toMap(d -> d.getDataSource().getId(), Function.identity()));

    Map<DataRecord, List<Criterion>> sourceCriteria = fetchSourceCriteriaMap(rules, criterionPrecedenceMap, recordMap);

    Set<DataRecord> recordsUsed = sourceCriteria.entrySet()
        .stream()
        .map(entry -> {
          final var source = entry.getKey().getDataSource();
          Either<Errors, Data> data = recordMap
              .get(source.getId())
              .getDataSource()
              .getData()
              .map(dataValue -> {
                final var dataBuilder = Data.builder();
                buildData(dataBuilder, entry.getValue(),
                    recordMap.get(source.getId())
                        .errorsOrData()
                        .get());
                return dataBuilder.build();
              });

          return DataRecord.builder()
              .dataSource(source)
              .data(data)
              .build();
        })
        .collect(Collectors.toSet());

    final var unmatchedDataSources = recordMap.values().stream()
        .map(e -> e.getDataSource())
        .filter(e -> !containsDataSource(recordsUsed, e))
        .map(e -> DataRecord.builder()
            .dataSource(e)
            .data(Either.right(Data.builder().build()))
            .build())
        .collect(Collectors.toSet());

    return Sets.union(recordsUsed, unmatchedDataSources);
  }

  private void buildData(final Data.Builder dataBuilder, final List<Criterion> criteria, final Data data) {
    // Implementation depending on criteria
  }

  public Map<Criterion, List<Precedence>> fetchCriterionPrecedenceMap(final Set<Rule> rules) {
    return rules.stream()
        .map(Rule::getFilter)
        .map(Filter::dataSourcePrecedence)
        .flatMap(source -> source.values().stream()
            .map(c -> Tuple.of(
                c.criterion(),
                new Precedence(source.sourceName(), c.value())
            )))
        .collect(Collectors.toMap(t -> t._1, t -> List.of(t._2), this::mergeAndSortLists));
  }

  private List<Precedence> mergeAndSortLists(List<Precedence> list1, List<Precedence> list2) {
    // Implementation for merging and sorting
    return null;
  }

  private boolean containsDataSource(final Set<DataRecord> records, final DataSource dataSource) {
    return records.stream().anyMatch(r -> r.getDataSource() == dataSource);
  }

  private Map<DataRecord, List<Criterion>> fetchSourceCriteriaMap(
      final Set<Rule> rules,
      final Map<Criterion, List<Precedence>> criterionSourcePrecedenceMap,
      final Map<String, DataRecord> dataSourceMap) {
    final var criteria = rules.stream()
        .flatMap(rule -> rule.getCriteria().stream())
        .collect(Collectors.toSet());

    return criteria.stream()
        .map(criterion -> Tuple.of(
            criterion,
            Optional.ofNullable(criterionSourcePrecedenceMap.get(criterion))
                .orElseThrow(() -> new IllegalArgumentException(
                    String.format("Unable to find criterion %s in precedence map", criterion)))))
        .map(t -> findFirstValidValue(t, dataSourceMap))
        .filter(t -> t._2.isPresent())
        .collect(ImmutableMap.toImmutableMap(t -> t._2.get(), t -> List.of(t._1),
            (criterionListA, criterionListB) -> ImmutableList.<Criterion>builder()
                .addAll(criterionListA)
                .addAll(criterionListB)
                .build()));
  }

  private Tuple2<Criterion, Optional<DataRecord>> findFirstValidValue(final Tuple2<Criterion, List<Precedence>> t, final Map<String, DataRecord> dataSourceMap) {
    return null;
  }

  private record DataSourcePrecedence(Set<DataFieldPrecedence> values, String sourceName) {
  }

  private record DataFieldPrecedence(Criterion criterion, String value) {
  }

  private record Filter(DataSourcePrecedence dataSourcePrecedence) {
  }

  public static class Precedence {
    public Precedence(final String sourceName, final String value) {
      // Implementation
    }
  }

  private static class Result {
    private final Set<DataRecord> dataRecords;

    private Result(Set<DataRecord> dataRecords) {
      this.dataRecords = dataRecords;
    }

    public Set<DataRecord> getDataRecords() {
      return dataRecords;
    }
  }

  public static class Rule {
    private final Filter filter;
    private final Set<Criterion> criteria;

    private Rule(Filter filter, Set<Criterion> criteria) {
      this.filter = filter;
      this.criteria = criteria;
    }

    public Set<Criterion> getCriteria() {
      // Logic for filtering criteria based on rule
      return criteria;
    }

    public Filter getFilter() {
      return filter;
    }
  }

  public static class Criterion {
  }

  static class DataRecord {
    public static DataRecord.Builder builder() {
      return new DataRecord.Builder();
    }

    public DataSource getDataSource() {
      return null;
    }

    public Either<Errors, Data> errorsOrData() {
      return null;
    }

    private static class Builder {
      // Builder implementation here
      public DataRecord build() {
        return null;
      }

      public Builder dataSource(final DataSource source) {
        return null;
      }

      public Builder data(final Either<Errors, Data> data) {
        return null;
      }
    }
  }

  private class DataSource {
    public String getId() {
      return null;
    }

    public Either<Errors, Data> getData() {
      return null;
    }
  }

  private class Errors {
  }

  static class Data {

    // Many fields
    public static Data.Builder builder() {
      return null;
    }

    static class Builder {
      // Builder implementation here
      public Data build() {
        return null;
      }
    }
  }
}

