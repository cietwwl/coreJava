package com.edu.orm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public interface EntityMetadata {

	public String getEntityName();

	public Map<String, String> getFields();

	public String getName();

	public String getPrimaryKey();

	public Collection<String> getIndexKeys();

	public String getVersionKey();

	public <PK extends Serializable> Class<PK> getPrimaryKeyClass();

	@SuppressWarnings("rawtypes")
	public <T extends IEntity> Class<T> getEntityClass();

}
