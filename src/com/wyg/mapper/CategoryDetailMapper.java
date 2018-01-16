package com.wyg.mapper;

import java.util.List;

import com.wyg.pojo.CategoryDetail;
public interface CategoryDetailMapper {
		public List<CategoryDetail> getAllConcretype();
		public List<CategoryDetail> getConcretypeById(int id);
		public String getDetailName(Long id);
		public long getTypeIdbyDetailId(long id);
		public CategoryDetail findCatedetailById(long id);
}
