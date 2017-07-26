package com.tgweb.hibernate.day01;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
//使用java.util.Date而不使用java.sql.Date
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest {

	private Configuration configuration;
	private SessionFactory sessionFactory;

	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	@After
	public void destory() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	///////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testPropertyUpdate() {
		// 属性节点：name,type(字段类型),access(setter/getter或反射),unique等等
		News news1 = new News();
		news1.setTitle("AASAS");
		news1.setAuthor("Mary");
		session.save(news1);
		session.clear();
		News news2 = session.get(News.class, news1.getId());
		System.out.println(news2);
	}

	@Test
	public void testIdGenerator() {
		// 不同的标识符生成方法的效果不一样，支持的数据库也不一样
		// increment:会存在并发安全问题
		// identity:底层数据库负责生成自动增长的标识符(比如MySQL auto_increment)，Oracle不支持
		// sequence:底层数据库提供的序列来生成标识符，MySQL不支持
		// hilo:按照High/Low高低算法生成主键值，不依赖底层数据库，适合所有的数据库
		// native:依据底层数据库驱动，自动选择时identity,sequence,hilo,跨平台的方式------常用
		News news1 = new News("C++", "peter", new Date());
		Serializable serializableId = session.save(news1);
		System.out.println(serializableId);
	}

	@Test
	public void testDynamicInsert() {
		// 动态插入时，只插入值不为null的部分
		News news1 = new News();
		news1.setAuthor("Mary");
		session.save(news1);
	}

	@Test
	public void testDynamicUpdate() {
		// 动态更新时，只更新值发生改变的部分，没有变的部分不构成SQL语句
		News news1 = session.get(News.class, 7L);
		news1.setAuthor("DDDD");
		session.update(news1);
	}

	@Test
	public void testDoWork() {
		// 获得Hibernate底层的JDBC的Connection
		session.doWork(new Work() {

			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection);
			}
		});
	}

	////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testEvict() {
		News news1 = session.get(News.class, 7L);
		News news2 = session.get(News.class, 8L);
		news1.setTitle("AA");
		news2.setTitle("BB");
		session.evict(news1);
	}

	@Test
	public void testDelete() {
		// 删除游离对象
		News news = new News();
		// 若数据库中没有该ID的数据记录的话，则会抛出异常
		news.setId(14L);

		// Hibernate只是对ID进行操作
		session.delete(news);

		// 删除持久化对象，commit（flush）时删除
		News news1 = session.get(News.class, 13L);
		session.delete(news1);
		// 删除持久化对象后，news中的Id默认不会被置null，可以设置使得delete方法调用时，将对象的id置空
	}

	@Test
	public void testMerge() {

	}

	@Test
	public void testSaveOrUpdate() {
		// OID来判断save或update,若id为空，则save
		// 若oid与unsaved-values相同则可以插入,不过插入的值不是unsaved-values,而是生成的ID
		News news1 = new News("C++", "peter", new Date());
		session.saveOrUpdate(news1);

		News news2 = new News("C++", "mark", new Date());
		// 若插入的ID不在数据库中，则抛出异常
		// news2.setId(1111L);
		news2.setId(0L);
		session.saveOrUpdate(news2);
	}

	@Test
	public void testUpdate() {
		/**
		 * 1、在更新持久化对象时，不需要显示的调用update方法，因为session的commit会调用flush
		 * 2、更新一个游离对象，需要显示的调用update对象，但是也会把一个已经作废的游离对象持久化
		 * 3、不盲目触发update方法，可以设置select-before-update,但是效率会降低
		 * 4、若对一个游离对象执行update,而数据库中没有这个游离对象的ID，则会抛出异常
		 * 5、在同一个Session内不能有两条OID(Object Id)相同的对象
		 */

		News news = session.get(News.class, 1L);

		transaction.commit();
		session.close();

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();

		news.setAuthor("may");
		// news.setId(121212L);

		session.update(news);
	}

	@Test
	public void testLoad() {
		/**
		 * get和load方法：
		 * 
		 * 1）get是立即执行检索，而load是延迟检索，在使用这个对象时才去检索<br/>
		 * 
		 * 2）若ID查询不到，在使用这个对象时，get返回Null,load抛出异常<br/>
		 * 
		 * 3)
		 * load可能会抛出懒加载异常org.hibernate.LazyInitializationException,如果session提前关闭，而这时需要使用这个对象
		 * 
		 */
		News news = session.load(News.class, 1L);
		System.out.println(news.getClass().getName());
		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		session.close();
		System.out.println(news);
		System.out.println(news.getClass().getName());
	}

	@Test
	public void testGet() {
		News news = session.get(News.class, 1L);
		System.out.println(news);
	}

	@Test
	public void testPersist() {
		// 持久化一个对象
		News news = new News();
		news.setTitle("C#");
		news.setAuthor("jack");
		news.setCreateDate(new Date());

		// 持久化数据的ID在persist方法之前设立的是不允许的，即对象不能有初始ID
		// news.setId(1111L);

		System.out.println(news);

		session.persist(news);

		// 持久化的数据的ID在persist方法之后修改是不允许的
		// news.setId(222L);
		System.out.println(news);
	}

	@Test
	public void testSave() {
		// 保存（持久化）一个对象
		News news = new News();
		news.setTitle("C#");
		news.setAuthor("jack");
		news.setCreateDate(new Date());

		// 持久化数据的ID在save方法之前设立的是无效的
		news.setId(1111L);

		System.out.println(news);

		session.save(news);

		// 持久化的数据的ID在save方法之后修改是不允许的
		// news.setId(222L);
		System.out.println(news);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testSessionClear() {
		News news1 = new News("Java", "james", new Date());
		session.save(news1);

		// 调用clear方法后Serializable为1L的对象不在Session中,再次Get时需要重新发送SQ语句
		News news2 = session.get(News.class, 1L);
		System.out.println(news2);
		session.clear();
		News news3 = session.get(News.class, 1L);
		System.out.println(news3);
	}

	@Test
	public void testSessionRefresh() {
		// 当数据库修改时，调用refresh可以使得Session和数据库同步
		// 如果是MySQL，MySQL的事务隔离级别需要修改
		News news1 = session.get(News.class, 1L);
		System.out.println(news1);
		session.refresh(news1);
		System.out.println(news1);
	}

	@Test
	public void testSessionFlush() {
		// 放Session缓存数据发送变化时，调用flush可以使得Session和数据库同步
		News news1 = session.get(News.class, 1L);
		System.out.println(news1);
		News news2 = session.get(News.class, 1L);
		System.out.println(news2);

		news1.setAuthor("james" + (int) (Math.random() * 10));
		System.out.println(news1);
		System.out.println(news2);

		// 隐式调用flush(),DEBUG单步调试可以看到
		// 是在commit()方法中先调用doFlush()，可能会发送SQL语句
		// 有时会显示或隐式调用flush():
		// 1)在执行HQL或者QBC查询时(Hibernate中共提供了三种检索方式：HQL(Hibernate Query
		// Language)、QBC、QBE(Query By
		// Example))会执行flush()，以便获得最新的数据(并不是数据库中的最新的状态，而是Session缓存中的最新数据)
		// 2)在generator为native时，调用session.save(),ID为数据库生成的，会立即发送SQL语句和调用flush()
		@SuppressWarnings("deprecation")
		News news3 = (News) session.createCriteria(News.class).list().get(0);
		System.out.println(news3);
	}

	@Test
	public void testSessionCache() {
		// Session的一级缓存
		News news1 = session.get(News.class, 1L);
		System.out.println(news1);
		News news2 = session.get(News.class, 1L);
		System.out.println(news2);
	}

	@Test
	public void testSessionSave() {
		// 主键的生成方式是native,所以在没有commit时也会在发送SQL语句，因为需要获取到ID作为Key，但不会插入数据，只是为了获得ID号
		News news1 = new News("Java", "james", new Date());
		Serializable serializableId = session.save(news1);
		System.out.println("serializableId: " + serializableId);
		System.out.println(news1);
	}

}
