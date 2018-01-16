package com.wyg.mapper;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.wyg.pojo.Evaluate;
import com.wyg.pojo.Order;
import com.wyg.pojo.OrderLine;
import com.wyg.pojo.Payway;
import com.wyg.pojo.PriceRank;
import com.wyg.pojo.Product;
import com.wyg.pojo.Receiver;
import com.wyg.pojo.Report;
import com.wyg.pojo.User;
import com.wyg.util.MybatisSqlSessionFactory;

public class TEST {
	@Test
	public void test()		//查大类
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		List<Product>list=mapper.getProductsByCategoryId(2l);
		List<Product>list2=mapper.getProductsByCategoryId(2l);
		List<Product>list3=mapper.getProductsByCategoryId(2l);
		System.out.println(list.size());
		System.out.println(list2.size());
		System.out.println(list3.size());
	}
	
	@Test
	public void test2()//查小类
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		List<Product>list=mapper.getProductsByDetailId(7L);
		System.out.println(list);
	}
	
	@Test
	public void test3()//查出版社
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		List<Product>list=mapper.getProductsByPublishId(5L);
		System.out.println(list);
	}
	
	@Test
	public void test4()		//查热门
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		List<Product>list=mapper.getHotProducts();
		System.out.println(list);
	}
	
	@Test
	public void test5()		//查大ID
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		CategoryDetailMapper mapper=session.getMapper(CategoryDetailMapper.class);
		long id=mapper.getTypeIdbyDetailId(7l);
		System.out.println(id);
	}
	
	@Test
	public void test6()		//查大name
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		CategoryMapper mapper=session.getMapper(CategoryMapper.class);
		String name=mapper.getCategoryName(2L);
		System.out.println(name);
	}
	
	@Test
	public void test7()		//查小name
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		CategoryDetailMapper mapper=session.getMapper(CategoryDetailMapper.class);
		String name=mapper.getDetailName(2L);
		System.out.println(name);
	}
	
	@Test
	public void test8()		//通过id查找书
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		Product product=mapper.getProductsById(2L);
		System.out.println(product);
	}
	
	@Test
	public void test9()		//通过name查找user
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		UserMapper mapper=session.getMapper(UserMapper.class);
		User user=mapper.findUserByName("jack");
		System.out.println(user);
	}
	
	@Test
	public void test10()		//查所有简报
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ReportMapper mapper=session.getMapper(ReportMapper.class);
		List<Report> list=mapper.getAllReport();
		System.out.println(list);
	}
	@Test
	public void test11()		//通过id查找user
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		UserMapper mapper=session.getMapper(UserMapper.class);
		User user=mapper.findUserById(2L);
		System.out.println(user);
	}
	
	@Test
	public void test12()		//通过id查找user
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ReceiverMapper mapper=session.getMapper(ReceiverMapper.class);
		mapper.insertReceiver(new Receiver(1, "jack", "jiangxi", "13666666666",new User(1)));
		session.commit();
	}
	
	@Test
	public void test13()		//通过id查找user
	{
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ReceiverMapper mapper=session.getMapper(ReceiverMapper.class);
		Receiver findReceiverByUserId = mapper.findReceiverByUserId(1L);
		System.out.println(findReceiverByUserId);
	}
	@Test
	public void test14(){	//插入Order
		SqlSession session=MybatisSqlSessionFactory.openSession();
		OrderMapper mapper=session.getMapper(OrderMapper.class);
		mapper.insertOrder(new Order(1L, 1, new Date().toString(), 100.00f, new Payway(1), "微支付", new User(1), new Receiver(1)));
		session.commit();
	}
	
	@Test
	public void test15(){		//通过用户id查询Order集合
		SqlSession session=MybatisSqlSessionFactory.openSession();
		OrderMapper mapper=session.getMapper(OrderMapper.class);
		List<Order> order = mapper.findOrderByUserId(1);
		System.out.println(order);
	}
	
	@Test
	public void test16(){		//插入订单项
		SqlSession session=MybatisSqlSessionFactory.openSession();
		OrderLineMapper mapper=session.getMapper(OrderLineMapper.class);
		mapper.insertOrderLine(new OrderLine(1, 100, 200, new Product(1),new Order(4), "精装版"));
		session.commit();
	}
	
	@Test
	public void test17(){		//通过用户id查询Orderline集合
		SqlSession session=MybatisSqlSessionFactory.openSession();
		OrderLineMapper mapper=session.getMapper(OrderLineMapper.class);
		Set<OrderLine> findOrderLineByOrderId = mapper.findOrderLineByOrderId(4);
		System.out.println(findOrderLineByOrderId);
	}
	
	@Test
	public void test18(){
		SqlSession session=MybatisSqlSessionFactory.openSession();
		OrderMapper mapper=session.getMapper(OrderMapper.class);
		System.out.println(mapper.getCurrentiId());
		
	}
	
	@Test
	public void test19(){
		SqlSession session=MybatisSqlSessionFactory.openSession();
		OrderMapper mapper=session.getMapper(OrderMapper.class);
		mapper.updateOrderById(new Order(1, 100, new Date().toString(), 300,new Payway(2), "已支付", new User(1), new Receiver(1)));
		session.commit();
	}
	
	@Test
	public void test20(){
		SqlSession session=MybatisSqlSessionFactory.openSession();
		RankMapper mapper=session.getMapper(RankMapper.class);
		List<PriceRank> list=mapper.findAllRank();
		System.out.println(list);
	}
	
	@Test
	public void test21(){
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		List<Product> list=mapper.getProductsByPriceODID(2);
		System.out.println(list);
	}
	
	@Test
	public void test22(){
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		List<Product> list=mapper.getProductsByPriceAndPublishODID(2, 3);
		System.out.println(list);
	}
	@Test
	public void test23(){
		SqlSession session=MybatisSqlSessionFactory.openSession();
		ProductMapper mapper=session.getMapper(ProductMapper.class);
		List<Product> list=mapper.getProductsByNewPublishODST(0);
		System.out.println(list);
	}
	
	@Test
	public void test24(){
		SqlSession session=MybatisSqlSessionFactory.openSession();
		EvaluateMapper mapper=session.getMapper(EvaluateMapper.class);
		mapper.insertEvaluate(new Evaluate(1, "", new Date().toString(), new User(1),new Product(1),0,"这个产品真好", 3));
		session.commit();
	}
	
	@Test
	public void test26(){
		SqlSession session=MybatisSqlSessionFactory.openSession();
		EvaluateMapper mapper=session.getMapper(EvaluateMapper.class);
		System.out.println(mapper.findEvaByProductId(1));
	}
	
	@Test
	public void test27(){
		SqlSession session=MybatisSqlSessionFactory.openSession();
		EvaluateMapper mapper=session.getMapper(EvaluateMapper.class);
		mapper.updateEva(new Evaluate("", new Date().toString(), new User(1), new Product(1), 1, "可以", 4));
		session.commit();
	}
	
}
