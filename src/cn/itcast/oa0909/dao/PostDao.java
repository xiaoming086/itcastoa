package cn.itcast.oa0909.dao;

import java.util.Set;

import cn.itcast.oa0909.dao.base.BaseDao;
import cn.itcast.oa0909.domain.Post;

public interface PostDao<T> extends BaseDao<T>{
	public Set<Post> getPostsByIDS(Long[] pids);
}
