package victor.training.fp.repo.custom;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    protected EntityManager entityManager;
    
    public CustomJpaRepositoryImpl(JpaEntityInformation<T,?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getByPrimaryKeys(Collection<ID> ids) {
        if (ids.isEmpty()) {
            return new ArrayList<T>();
        } else {
            return entityManager.createQuery("SELECT e FROM " + getDomainClass().getSimpleName() + " e WHERE e.id IN (:ids)")
                .setParameter("ids", ids).getResultList();
        }
    }

	@Override
	public T getExactlyOne(ID id) {
		return findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("No " + getDomainClass().getSimpleName() + " with id " + id));
	}
	
}
