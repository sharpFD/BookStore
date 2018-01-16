package com.wyg.mapper;

import java.util.List;
import com.wyg.pojo.Category;

public interface CategoryMapper {
		public List<Category> findAlltype();
		public String getCategoryName(Long type);
}
