package com.asiainfo.dsession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: TODO
 * 
 * @author       zq
 * @date         2017年8月29日  上午11:20:58
 * Copyright: 	  北京亚信智慧数据科技有限公司
 */
public class SessionEnumerator<E> implements Enumeration<E> {

	Iterator<E> iterator = null;
	
	public SessionEnumerator(Collection<E> collection, boolean clone) {
		this(collection.iterator(), clone);
	}

	public SessionEnumerator(Iterator<E> iterator, boolean clone) {

		super();
		if (!clone) {
			this.iterator = iterator;
		} else {
			List<E> list = new ArrayList<>();
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
			this.iterator = list.iterator();
		}
	}
	
	/* 
	 * @Description: TODO
	 * @return
	 * @see java.util.Enumeration#hasMoreElements()
	 */
	@Override
	public boolean hasMoreElements() {
		return this.iterator.hasNext();
	}

	/* 
	 * @Description: TODO
	 * @return
	 * @see java.util.Enumeration#nextElement()
	 */
	@Override
	public E nextElement() {
		return this.iterator.next();
	}
}
