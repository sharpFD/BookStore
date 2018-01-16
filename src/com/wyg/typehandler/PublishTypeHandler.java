package com.wyg.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.wyg.pojo.Publish;

public class PublishTypeHandler extends BaseTypeHandler<Publish>{

	//遇到PhoneNumber参数的时候应该如何在ps中设置值
		@Override
		public void setNonNullParameter(PreparedStatement ps, int i, Publish parameter, JdbcType jdbcType)
				throws SQLException {
			ps.setLong(i, parameter.getAsLong());
		}

		//查询中遇到PhoneNumber类型的应该如何封装(使用列名封装)
		@Override
		public Publish getNullableResult(ResultSet rs, String columnName) throws SQLException {
			return new Publish(rs.getLong(columnName));
		}

		//查询中遇到PhoneNumber类型的应该如何封装(使用列的下标)
		@Override
		public Publish getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
			return new Publish(rs.getLong(columnIndex));
		}

		//CallableStatement使用中遇到了PhoneNumber类型的应该如何封装
		@Override
		public Publish getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
			return new Publish(cs.getLong(columnIndex));
		}

}
