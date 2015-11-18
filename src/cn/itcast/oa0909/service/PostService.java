package cn.itcast.oa0909.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import cn.itcast.oa0909.domain.Post;

public interface PostService {
	public Collection<Post> getAllPost();
	
	public void updatePost(Post post);
	
	public void savePost(Post post);
	
	public void deletePost(Serializable id);
	
	public Post getPostById(Serializable id);
	
	public Set<Post> getPostsByIds(Long[] pids);
}
