package sample;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Either;

import java.security.PublicKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static java.util.stream.Nodes.collect;

public class DataService {

  public Set<DataRecord> fetchData(Set<Rule> rules, Result result) {
    Map<Criterion, List<Precedence>> criterionPrecedenceMap = fetchCriterionPrecedenceMap(rules);
    Map<String, DataRecord> recordMap = result.getDataRecords()
        .stream()
        .collect(toMap(d -> d.getDataSource().getId(), identity()));

    Map<DataRecord, List<Criterion>> sourceCriteria = fetchSourceCriteriaMap(rules, criterionPrecedenceMap, recordMap);

    Set<DataRecord> recordsUsed = sourceCriteria.entrySet()
        .stream()
        .map(entry -> {
          var source = entry.getKey().getDataSource();
          Either<Errors, Data> data = recordMap
              .get(source.getId())
              .getDataSource()
              .getData()
              .map(dataValue -> {
                var dataBuilder = Data.builder();
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

    var unmatchedDataSources = recordMap.values().stream()
        .map(e -> e.getDataSource())
        .filter(e -> !containsDataSource(recordsUsed, e))
        .map(e -> DataRecord.builder()
            .dataSource(e)
            .data(Either.right(Data.builder().build()))
            .build())
        .collect(Collectors.toSet());

    return Sets.union(recordsUsed, unmatchedDataSources);
  }

  private void buildData(Data.Builder dataBuilder, List<Criterion> criteria, Data data) {
    // Implementation depending on criteria
  }

  // if the order is CRITICAL, change to TreeSet by the natural order of Precedence implements Comparable
  // if the order is not critical stick to List
//  List<Tuple<Criterion, List<Precedence>>>
  public Map<Criterion, List<Precedence>> fetchCriterionPrecedenceMap(Set<Rule> rules) {
    return rules.stream()
        .map(Rule::getFilter)
        .map(Filter::dataSourcePrecedence)
        .flatMap(source -> source.values().stream()
            .map(field -> Tuple.of(
                field.criterion(),
                new Precedence(source.sourceName(), field.value())
            )))
//        .collect(Collectors.toMap(t -> t._1, t -> List.of(t._2), this::mergeAndSortLists));
        .collect(groupingBy(t -> t._1, mapping(t -> t._2, toList())));
//        .collect(Collectors.groupingBy(t -> t._1, Collectors.mapping(t -> t._2, Collectors.toCollection(TreeSet::new))));
  }

  private List<Precedence> mergeAndSortLists(List<Precedence> list1, List<Precedence> list2) {
    // Implementation for merging and sorting
    return null;
  }

  private boolean containsDataSource(Set<DataRecord> records, DataSource dataSource) {
    return records.stream().anyMatch(r -> r.getDataSource() == dataSource);
  }

  private Map<DataRecord, List<Criterion>> fetchSourceCriteriaMap(
      Set<Rule> rules,
      Map<Criterion, List<Precedence>> criterionSourcePrecedenceMap,
      Map<String, DataRecord> dataSourceMap) {
    var criteria = rules.stream()
        .flatMap(rule -> rule.getCriteria().stream())
        .collect(Collectors.toSet());


    if (!criterionSourcePrecedenceMap.keySet().containsAll(criteria)) {
      throw new IllegalArgumentException("Not all criteria are present in the precedence map");
    }

    return criteria.stream()
        .map(criterion -> Tuple.of(criterion, criterionSourcePrecedenceMap.get(criterion)))
        .map(t -> findFirstValidValue(t, dataSourceMap))
        .filter(t -> t._2.isPresent())
        .collect(ImmutableMap.toImmutableMap(t -> t._2.get(), t -> List.of(t._1), //(map1, map2)=> map1+map2
            (map1, map2) -> ImmutableList.<Criterion>builder()
            // // TODO victorrentea 2024-01-31:  POTENTIAL PEFORMANCE ISSUE!!
                .addAll(map1)
                .addAll(map2)
                .build()));
  }
//  record WTF(Criterion criterion, List<Precedence> precedenceList) {
//  }

  private Tuple2<Criterion, Optional<DataRecord>> findFirstValidValue(Tuple2<Criterion, List<Precedence>> t, Map<String, DataRecord> dataSourceMap) {
    return null;
  }

  private record DataSourcePrecedence(Set<DataFieldPrecedence> values, String sourceName) {
  }

  private record DataFieldPrecedence(Criterion criterion, String value) {
  }

  private record Filter(DataSourcePrecedence dataSourcePrecedence) {
  }

  public static class Precedence implements Comparable<Precedence>{
    private final String value;

    public Precedence(String sourceName, String value) {
      // Implementation
      this.value = value;
    }
    public int compareTo(Precedence other) {
      // Implementation
      return value.compareTo(other.value);
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

      public Builder dataSource(DataSource source) {
        return null;
      }

      public Builder data(Either<Errors, Data> data) {
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

