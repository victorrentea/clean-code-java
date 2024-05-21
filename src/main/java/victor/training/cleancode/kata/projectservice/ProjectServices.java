package victor.training.cleancode.kata.projectservice;

import lombok.Data;

import static victor.training.cleancode.kata.projectservice.ProjectServiceStatus.SUBSCRIBED;

@Data // domain model intern.= nimanui nu-i pasa daca-l schimb
public class ProjectServices {
   private ProjectServiceStatus status;
   private Service service;

   public boolean isSubscribed() {
     return getStatus() == SUBSCRIBED;
   }
}
