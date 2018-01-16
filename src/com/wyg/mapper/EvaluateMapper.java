package com.wyg.mapper;

import java.util.List;

import com.wyg.pojo.Evaluate;

public interface EvaluateMapper {
	void insertEvaluate(Evaluate evaluate);
	List<Evaluate> getUserProEvaluates(long userid,long productid);
	List<Evaluate> findEvaByProductId(long productid);
	Evaluate getuniqueEva(long userid,long productid,long orderid);
	void updateEva(Evaluate newevaluate);
}
