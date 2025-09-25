package victor.training.cleancode.mass;

public abstract class BaseService<D extends DAO> {
  private D dao;

  public D getDao() {
    return dao;
  }

  public void setDao(D dao) {
    this.dao = dao;
  }
}
