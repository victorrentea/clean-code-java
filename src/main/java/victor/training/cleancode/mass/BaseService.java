package victor.training.cleancode.mass;

public abstract class BaseService<D extends DAO> {
  private D dao;

  protected D getDao() {
    return dao;
  }

  protected void setDao(D dao) {
    this.dao = dao;
  }
}
