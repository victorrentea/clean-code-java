package victor.training.cleancode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


import java.util.function.Predicate;

@RequiredArgsConstructor
@Slf4j
public class AsyncDoubleTabVerifier<T> {
  private final String title;
  private final TimeUnit waitTimeUnit;
  private final long waitDuration;
  private final ExecutorService executor;
  //  private final Map<T, T> fired = new ConcurrentHashMap<>();
  private final Set<T> fired = Collections.synchronizedSet(new HashSet<>());

  /**
   * This method will create async process to verify the condition twice, where the second verification will wait after the input duration to process, and
   * then to run the action if the verify result is positive.
   */
  public void actionIfPass(T t, Predicate<T> condition, Action action) {

    if (!fired.add(t)) {
      log.info("Verification of \"{}\" for {} already started and yet to complete", title, t);
      return;
    }
    log.info("Verification of \"{}\" for {} starting...", title, t);
    CompletableFuture.supplyAsync(() -> {
          return condition.and(target -> waitForSecondCheck(target)).and(condition).test(t);
        }, executor)
        .thenAcceptAsync(passed -> {
          if (passed) {
            log.info("Verification of \"{}\" is positive for {}, perform associate action.", title, t);
            action.perform();
          } else {
            log.warn("Verification of \"{}\" is negative for {}.", title, t);
          }
        })
        .whenComplete((unused, e) -> {
          fired.remove(t);
          if (e != null) {
            log.warn("Verification of \"{}\" failed for {}\nCause: {}", title, t, e, e);
          }
        });
  }

  private boolean waitForSecondCheck(T t) {
    try {
      log.info("Verification of \"{}\" for {} begin to wait for {} {} before second verification.", title, t, waitDuration, waitTimeUnit);
      waitTimeUnit.sleep(waitDuration);
      log.info("Verification of \"{}\" for {} had been waited for {} {}, continue to run the second verification.", title, t, waitDuration, waitTimeUnit);

    } catch (InterruptedException e) {
      log.warn("Verification of \"{}\" for {} failed while waiting to run the second verification. Cause:\n{}", title, t, e);
      return false;
    }
    return true;
  }


  //Functional Interface
  public interface Action {
    void perform();
  }
}
