package victor.training.cleancode;

import lombok.Data;
import lombok.Value;

import java.util.Objects;

@Value // care e mai bun decat @Data
// = @Data + toate campurile "private final"
// = getter allargsconstructor hash/eq tostring +  toate campurile "private final"
public class FullName {
  String firstName;
  String lastName;
}