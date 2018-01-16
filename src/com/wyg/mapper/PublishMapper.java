package com.wyg.mapper;

import java.util.List;
import com.wyg.pojo.Publish;

public interface PublishMapper {
		public List<Publish> getAllPublish();
		public Publish findPublishById(long id);
}
