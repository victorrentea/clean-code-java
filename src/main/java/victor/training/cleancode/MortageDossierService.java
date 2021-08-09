package victor.training.cleancode;

import lombok.RequiredArgsConstructor;
import victor.training.cleancode.pretend.Service;

class MortageDossier{
   //50 fields
}
@Service
@RequiredArgsConstructor
public class MortageDossierService {
   private final MortageDossierRepo repo;
   private final MortageDossierServiceSubService subService;

   // 50 public methods
   public void method() {
      System.out.println("Other1");
      System.out.println("Other1");
      System.out.println("Other1");
      System.out.println("Other1");
      System.out.println("Other1");

      subService.toMove();
   }

}

@Service
@RequiredArgsConstructor
class MortageDossierServiceSubService {
   private final MortageDossierRepo repo;

   public void toMove() {
      System.out.println(repo.count());
      System.out.println("Other2");
      System.out.println("Other2");
      System.out.println("Other2");
      meth2();
   }

   private void meth2() {
      System.out.println("Other2");
      System.out.println("Other2");
   }
}

interface MortageDossierRepo {
   int count();
}
