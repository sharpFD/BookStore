package com.wyg.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import com.wyg.mapper.CategoryMapper;
import com.wyg.mapper.EvaluateMapper;
import com.wyg.mapper.OrderLineMapper;
import com.wyg.mapper.OrderMapper;
import com.wyg.mapper.ProductMapper;
import com.wyg.mapper.PublishMapper;
import com.wyg.mapper.RankMapper;
import com.wyg.mapper.ReceiverMapper;
import com.wyg.mapper.ReportMapper;
import com.wyg.mapper.UserMapper;
import com.wyg.mapper.CategoryDetailMapper;
import com.wyg.pojo.Category;
import com.wyg.pojo.CategoryDetail;
import com.wyg.pojo.Evaluate;
import com.wyg.pojo.Order;
import com.wyg.pojo.OrderLine;
import com.wyg.pojo.PriceRank;
import com.wyg.pojo.Product;
import com.wyg.pojo.Publish;
import com.wyg.pojo.Receiver;
import com.wyg.pojo.Report;
import com.wyg.pojo.User;

public class Loaddata {
		private static SqlSession session=MybatisSqlSessionFactory.openSession();
		private static CategoryMapper categoryMapper=session.getMapper(CategoryMapper.class);
		private static CategoryDetailMapper categoryDetailMapper=session.getMapper(CategoryDetailMapper.class);
		private static ProductMapper productMapper=session.getMapper(ProductMapper.class);
		private static PublishMapper publishMapper=session.getMapper(PublishMapper.class);
		private static UserMapper userMapper=session.getMapper(UserMapper.class);
		private static ReportMapper reportMapper=session.getMapper(ReportMapper.class);
		private static OrderLineMapper orderLineMapper=session.getMapper(OrderLineMapper.class);
		private static RankMapper rankMapper=session.getMapper(RankMapper.class);
		private static OrderMapper orderMapper=session.getMapper(OrderMapper.class);
		private static ReceiverMapper receiverMapper=session.getMapper(ReceiverMapper.class);
		private static EvaluateMapper evaluateMapper=session.getMapper(EvaluateMapper.class);
		public static String getTypeName(String type)			//通过type查大类型名字
		{
			 String categoryName = categoryMapper.getCategoryName(Long.parseLong(type));
			// session.commit();
			 return categoryName;
		}
	
		
		public static String getConcretypeName(String type)				//通过concretype查小类型名字
		{
			 String detailName = categoryDetailMapper.getDetailName(Long.parseLong(type));
			 //session.commit();
			 return detailName;
		}
	
		public static List<Category> getAlltype()
		{
			List<Category> list=categoryMapper.findAlltype();
			//session.commit();
			return list;
		}
		
		public static List<CategoryDetail> getConcretypesByid(int id)
		{
			List<CategoryDetail>list=categoryDetailMapper.getConcretypeById(id);
			//session.commit();
			return list;
		}
		public static List<Product> getHotProducts()
		{
			List<Product>list=productMapper.getHotProducts();
			//session.commit();
			return list;
		}
		public static Long getCategoryIdbyDetailId(Long id)
		{
			Long i=categoryDetailMapper.getTypeIdbyDetailId(id);
			//session.commit();
			return i;
		}
		public static List<Product> getHotSalesProducts()
		{
			List<Product>list=productMapper.getHotSalesProducts();
			//session.commit();
			return list;
		}
		public static List<Publish> getAllPublish()
		{
			List<Publish> allPublish = publishMapper.getAllPublish();
			//session.commit();
			return allPublish;
		}
		
		public static List<Product> showProductsByCategoryId(long id)
		{
			List<Product> productsByCategoryId = productMapper.getProductsByCategoryId(id);
			//session.commit();
			return productsByCategoryId;
		}
		public static List<Product> showProductsByDetailId(long id)
		{
			List<Product> productsByDetailId = productMapper.getProductsByDetailId(id);
			//session.commit();
			return productsByDetailId;
		}
		
		public static Product getProductById(long id)
		{
			Product productsById = productMapper.getProductsById(id);
			//session.commit();
			return productsById;
		}
		public static User findUserByName(String name)
		{
			User findUserByName = userMapper.findUserByName(name);
			//session.commit();
			return findUserByName;
		}
		
		public static List<Report> getAllReport()
		{
			List<Report> allReport = reportMapper.getAllReport();
			//session.commit();
			return allReport;
		}
		
		public static  User updateUser(User newuser)
		{
			userMapper.updateUser(newuser);
			session.commit();
			//session.close();
			return newuser;
		}
		public static String showBookVersion(String version)
		{
			if(version.equals("1"))return"【精装版】";
			else if(version.equals("2"))return"【简装版】";
			else return"【收藏版】";
		}
		
		public static double showPrice(String version,Product product)
		{
			if(version.equals("1"))return product.getPrice();
			else if(version.equals("2"))return product.getPrice2();
			else return product.getPrice3();
		}
		public static String getTypeNameById(long id)
		{
			Product product=getProductById(id);
			long detailid = product.getCate_detail().getId();
			Long cateid = getCategoryIdbyDetailId(detailid);
			return getTypeName(cateid+"");
		}
		
		public static String getConcretypeNameById(long id)
		{
			Product product=getProductById(id);
			long detailid = product.getCate_detail().getId();
			return getConcretypeName(detailid+"");
		}
		public static void insertOrderlines(OrderLine orderLine)
		{
			orderLineMapper.insertOrderLine(orderLine);
			System.out.println("插入Orderlines");
			session.commit();
			//session.close();
		}
		
		public static long insertOrder(Order order)
		{
			orderMapper.insertOrder(order);
			long id=orderMapper.getCurrentiId();
			System.out.println("插入Order");
			session.commit();
		//	session.close();
			return id;
		}
		
		public static void insertReceiver(Receiver receiver)
		{
			receiverMapper.insertReceiver(receiver);
			System.out.println("插入Receiver");
			session.commit();
			//session.close();
		}
		
		public static void updateStock(Product product)
		{
			productMapper.updateStock(product);
			session.commit();
			//session.close();
		}
		public static void updatesalenum(Product product)
		{
			productMapper.updatesalenum(product);
			session.commit();
		}
		
		public static void updateOrderById(Order order)
		{
			orderMapper.updateOrderById(order);
			session.commit();
			//session.close();
		}
		
		public static Order findOrderByOrderId(long id)
		{
			Order order= orderMapper.findOrderByOrderId(id);
			Set<OrderLine> set=findOrderLineByOrderId(id);
			order.setOrderlines(set);
			return order;
		}
		public static Set<OrderLine> findOrderLineByOrderId(long id)
		{
			return orderLineMapper.findOrderLineByOrderId(id);
		}
		
		//通过用户id得到order，order里面包含set集合
				public static List<Order> findOrderByUserId(long id)
				{
					List<Order> getorder = orderMapper.findOrderByUserId(id);
					//一个用户有多个order，list装
					//一个order有多个orderline，用set装
					for(int i=0;i<getorder.size();i++){
					Set<OrderLine>set=findOrderLineByOrderId(getorder.get(i).getId());
					getorder.get(i).setOrderlines(set);
					}
					return getorder;
				}
				
				public static List<OrderLine> setToList(Set<OrderLine> set)
				{
					Iterator<OrderLine> iterator=set.iterator();
					List<OrderLine>list=new ArrayList<>();
					while(iterator.hasNext())
					{
						list.add(iterator.next());
					}
					return list;
				}
				
				public static String getPayWayName(long id)
				{
					if(id==1) return "银联支付";
					else if(id==2) return "支付宝支付";
					else if(id==3) return "快钱支付";
					else return "环迅支付";
				}
				
				public static List<PriceRank> findAllRank()
				{
					List<PriceRank> list=rankMapper.findAllRank();
					return list;
				}
				/*通过价格筛选*/
				public static List<Product> getProductsByPriceODID(long id)
				{
					List<Product> productsByPriceODID = productMapper.getProductsByPriceODID(id);
				//	session.commit();
					return productsByPriceODID;
				}
				
				public static List<Product> getProductsByPriceODPR(long id)
				{
					List<Product> productsByPriceODPR = productMapper.getProductsByPriceODPR(id);
				//	session.commit();
					return productsByPriceODPR;
				}
				
				public static List<Product> getProductsByPriceODST(long id)
				{
					List<Product> productsByPriceODST = productMapper.getProductsByPriceODST(id);
				//	session.commit();
					return productsByPriceODST;
				}
				
				public static List<Product> getProductsByPriceODSA(long id)
				{
					List<Product> productsByPriceODSA = productMapper.getProductsByPriceODSA(id);
				//	session.commit();
					return productsByPriceODSA;
				}
				
				/*通过出版社筛选*/
				public static List<Product> getProductsByPublishODID(long id)
				{
					List<Product> productsByNewPublishODID = productMapper.getProductsByNewPublishODID(id);
					//session.commit();
					return productsByNewPublishODID;
				}
				
				public static List<Product> getProductsByPublishODST(long id)
				{
					List<Product> productsByNewPublishODST = productMapper.getProductsByNewPublishODST(id);
					//session.commit();
					return productsByNewPublishODST;
				}
				
				public static List<Product> getProductsByPublishODPR(long id)
				{
					List<Product> productsByNewPublishODPR = productMapper.getProductsByNewPublishODPR(id);
					//session.commit();
					return productsByNewPublishODPR;
				}
				
				public static List<Product> getProductsByPublishODSA(long id)
				{
					List<Product> productsByNewPublishODSA = productMapper.getProductsByNewPublishODSA(id);
				//	session.commit();
					return productsByNewPublishODSA;
				}
				/*通过两个条件共同筛选*/
				public static List<Product>getProductByPriceAndPublishODID(long priceid,long publishid)
				{
					List<Product> productsByPriceAndPublishODID = productMapper.getProductsByPriceAndPublishODID(priceid, publishid);
				//	session.commit();
					return productsByPriceAndPublishODID;
				}
				
				public static List<Product>getProductByPriceAndPublishODSA(long priceid,long publishid)
				{
					List<Product> productsByPriceAndPublishODSA = productMapper.getProductsByPriceAndPublishODSA(priceid, publishid);
					//session.commit();
					return productsByPriceAndPublishODSA;
				}
				
				public static List<Product>getProductByPriceAndPublishODPR(long priceid,long publishid)
				{
					List<Product> productsByPriceAndPublishODPR = productMapper.getProductsByPriceAndPublishODPR(priceid, publishid);
					//session.commit();
					return productsByPriceAndPublishODPR;
				}
				
				public static List<Product>getProductByPriceAndPublishODST(long priceid,long publishid)
				{
					List<Product> productsByPriceAndPublishODST = productMapper.getProductsByPriceAndPublishODST(priceid, publishid);
					//session.commit();
					return productsByPriceAndPublishODST;
				}
				
				public static void insertEvaluate(Evaluate evaluate)
				{
					evaluateMapper.insertEvaluate(evaluate);
					session.commit();
					//session.close();
				}
				
				public static List<Evaluate> getEvaluate(long userid,long productid)
				{
					return evaluateMapper.getUserProEvaluates(userid, productid);
				}
				
				public static int isEva(List<Evaluate> list)	//返回0表示有未评论
				{
					int flag=1;
					for(int i=0;i<list.size();i++)
					{
						if(list.get(i).getState()==0)flag=0;
					}
					System.out.println("flag="+flag);
					return flag;
				}
				
				public static List<Evaluate> findEvaByProductId(long productid)
				{
					return evaluateMapper.findEvaByProductId(productid);
				}
				
				public static Evaluate getUniqueEva(long userid,long productid,long orderid)
				{
					return evaluateMapper.getuniqueEva(userid, productid, orderid);
				}
				
				public static void updateEva(Evaluate newEva)
				{
					evaluateMapper.updateEva(newEva);
					session.commit();
				}
				
				public static void main(String[] args) {
					Evaluate evaluate=new Evaluate(1, "", new Date().toString(), new User(1),new Product(1), 0, "", 0);
					insertEvaluate(evaluate);
					
				}
}