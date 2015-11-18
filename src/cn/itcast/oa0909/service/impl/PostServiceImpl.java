package cn.itcast.oa0909.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa0909.dao.PostDao;
import cn.itcast.oa0909.domain.Post;
import cn.itcast.oa0909.service.PostService;

@Service("postService")
public class PostServiceImpl implements PostService{
	@Resource(name="postDao")
	private PostDao postDao;

	public Collection<Post> getAllPost() {
		// TODO Auto-generated method stub
		return this.postDao.getAllEntry();
	}

	@Transactional(readOnly=false)
	public void updatePost(Post post) {
		// TODO Auto-generated method stub
		this.postDao.updateEntry(post);
	}

	@Transactional(readOnly=false)
	public void savePost(Post post) {
		// TODO Auto-generated method stub
		this.postDao.saveEntry(post);
	}

	@Transactional(readOnly=false)
	public void deletePost(Serializable id) {
		// TODO Auto-generated method stub
		this.postDao.deleteEntry(id);
	}

	public Post getPostById(Serializable id) {
		// TODO Auto-generated method stub
		return (Post)this.postDao.getEntryById(id);
	}

	public Set<Post> getPostsByIds(Long[] pids) {
		// TODO Auto-generated method stub
		return this.postDao.getPostsByIDS(pids);
	}
	
}
