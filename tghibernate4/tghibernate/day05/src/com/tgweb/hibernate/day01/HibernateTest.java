package com.tgweb.hibernate.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testHibernateSecondLevelCache5() {
		// iterate()
		Department department = session.get(Department.class, 70L);
		System.out.println(department.getName());
		System.out.println(department.getEmployees().size());

		Query query1 = session.createQuery("FROM Employee e WHERE e.department.id = 70");

		// 这样做会发送另一条的SELECT语句
		// List<Employee> emps = query1.list();
		// System.out.println(emps.size());

		// 在缓存中的可以直接取出来
		Iterator<Employee> iterator1 = query1.iterate();
		while (iterator1.hasNext()) {
			System.out.println(iterator1.next());
		}

		Query query2 = session.createQuery("FROM Employee e WHERE e.department.id = 80");
		// 不在缓存中的则会根据id一条一条的去查找
		Iterator<Employee> iterator2 = query2.iterate();
		while (iterator2.hasNext()) {
			System.out.println(iterator2.next());
		}
	}

	@Test
	public void testHibernateSecondLevelCache4() {
		// 时间戳缓存,T1,T2,T3时刻

		String hql = "FROM Employee";
		Query query = session.createQuery(hql);
		// 配置文件中需要设置cache.use_query_cache为true
		query.setCacheable(true);

		List<Employee> emps1 = query.list();
		System.out.println(emps1.size());

		// 进行一次手动更新
		Employee employee = session.get(Employee.class, 80L);
		employee.setSalary(1111);

		List<Employee> emps2 = query.list();
		System.out.println(emps2.size());

		Employee employee2 = session.get(Employee.class, 80L);
		// 不一定就是修改后的值，取决于T1，T2，T3之间的时间差觉得是否去重新SELECT
		System.out.println(employee2.getSalary());
	}

	@Test
	public void testHibernateSecondLevelCache3() {
		// 查询缓存

		// HQL
		String hql = "FROM Employee";
		Query query = session.createQuery(hql);
		// 配置文件中需要设置cache.use_query_cache为true
		query.setCacheable(true);

		List<Employee> emps1 = query.list();
		System.out.println(emps1.size());

		List<Employee> emps2 = query.list();
		System.out.println(emps2.size());

		// QBC
		Criteria criteria = session.createCriteria(Employee.class);
		// setCacheable需要设置为true
		criteria.setCacheable(true);
		List<Employee> emps3 = criteria.list();
		System.out.println(emps3.size());

		List<Employee> emps4 = criteria.list();
		System.out.println(emps4.size());
	}

	@Test
	public void testHibernateSecondLevelCache2() {
		// 集合级别的二级缓存

		Department department1 = session.get(Department.class, 70L);
		System.out.println(department1);
		System.out.println(department1.getEmployees().size());

		transaction.commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();

		Department department2 = session.get(Department.class, 70L);
		System.out.println(department2);
		System.out.println(department2.getEmployees().size());
	}

	@Test
	public void testHibernateSecondLevelCache() {
		// 一级缓存Session，二级缓存SessionFactory的一个缓存插件
		// 加入二级缓存后，也只需要一次SELECT即可

		// 类级别的二级缓存
		Employee employee1 = session.get(Employee.class, 1L);
		System.out.println(employee1);

		transaction.commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();

		Employee employee2 = session.get(Employee.class, 1L);
		System.out.println(employee2);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 检索方式和配置文件之间的关系,比如显示的使用HQL查询和配置文件中fetch之间的关系

	@Test
	public void testNativeSQL() {
		// 普通的本地SQL查询
		// 获得每个部门中工资最高的员工的信息
		String sql = "SELECT e.id,e.name,e.salary,e.department_id FROM ( SELECT * FROM employee t ORDER BY t.salary DESC) AS e GROUP BY e.department_id";
		Query query = session.createSQLQuery(sql);
		List<Object[]> lst = query.list();
		for (Object[] objs : lst)
			System.out.println(Arrays.asList(objs));
	}

	@Test
	public void testHQLUpdate() {
		// HQL更新操作
		String hql = "UPDATE Employee e SET e.salary = :salary where e.id =  :id";
		Query query = session.createQuery(hql);
		query.setInteger("salary", 8888);
		query.setLong("id", 80L);
		query.executeUpdate();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testCriteria5() {
		// 分页查询
		Criteria criteria = session.createCriteria(Employee.class);
		int pageSize = 5;
		int pageNo = 3;
		criteria.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
	}

	@Test
	public void testCriteria4() {
		// 排序查询
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.addOrder(Order.asc("email"));
		criteria.addOrder(Order.desc("salary"));
		criteria.list();
	}

	@Test
	public void testCriteria3() {
		// 统计查询
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.max("salary"));
		System.out.println(criteria.uniqueResult());
	}

	@Test
	public void testCriteria2() {
		// 复杂的Criteria查询(组合查询)
		Criteria criteria = session.createCriteria(Employee.class);

		// AND
		Conjunction conjunction = Restrictions.conjunction();
		Department department = new Department();
		department.setId(80L);
		conjunction.add(Restrictions.eq("department", department));
		conjunction.add(Restrictions.like("name", "DEPT", MatchMode.ANYWHERE));
		System.out.println(conjunction);

		// OR
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.isNotNull("department"));
		disjunction.add(Restrictions.ge("salary", 5000L));
		System.out.println(disjunction);

		criteria.add(conjunction);
		criteria.add(disjunction);

		criteria.list();
	}

	@Test
	public void testCriteria() {
		Criteria criteria = session.createCriteria(Employee.class);
		// criteria.add(Restrictions.lt("salary", 5000));
		criteria.add(Restrictions.lt("salary", new Integer(5000)));
		Department department = new Department();
		department.setId(80L);
		criteria.add(Restrictions.eq("department", department));

		// where and 查询，完全基于对象的操作
		List<Employee> e = criteria.list();
		System.out.println(e);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testInnerJoin4() {
		// 这种查询会将查询到的结果根据类名通过mapping中的access方式配置
		String hql = "SELECT  d FROM Department d INNER JOIN  d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		System.out.println(lst.size());
		lst = new ArrayList<>(new LinkedHashSet<>(lst));
		System.out.println(lst.size());
		for (Department d : lst) {
			System.out.println(d + " " + d.getEmployees().size());
		}
	}

	@Test
	public void testInnerJoin3() {
		// 这种查询会将查询到的结果根据类名通过mapping中的access方式配置
		String hql = "SELECT DISTINCT d FROM Department d INNER JOIN  d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		for (Department d : lst) {
			System.out.println(d + " " + d.getEmployees().size());
		}
	}

	@Test
	public void testInnerJoin2() {
		// 这种查询会将查询到的结果根据类名通过mapping中的access方式配置
		String hql = "SELECT d FROM Department d INNER JOIN  d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		for (Department d : lst) {
			System.out.println(d + " " + d.getEmployees().size());
		}
	}

	@Test
	public void testInnerJoin() {
		// 内连接
		// 这种hql查询会将查询到的结果成为Object数组
		String hql = "FROM Department d INNER JOIN  d.employees";
		Query query = session.createQuery(hql);
		List<Object[]> lst = query.list();
		for (Object[] objs : lst) {
			// 和普通左外连接一样，需要时再发送SELECT获取
			System.out.println(Arrays.asList(objs));
		}
	}

	@Test
	public void testInnerJoinFetch3() {
		// 迫切内连接
		String hql = "SELECT d FROM Department d INNER JOIN FETCH d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		System.out.println(lst.size());
		lst = new ArrayList<>(new LinkedHashSet<>(lst));
		System.out.println(lst.size());
		for (Department d : lst) {
			System.out.println(d + " " + d.getEmployees().size());
		}
	}

	@Test
	public void testInnerJoinFetch2() {
		// 迫切内连接
		String hql = "SELECT DISTINCT d FROM Department d INNER JOIN FETCH d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		System.out.println(lst.size());
		for (Department d : lst) {
			System.out.println(d + " " + d.getEmployees().size());
		}
	}

	@Test
	public void testInnerJoinFetch() {
		// 迫切内连接
		String hql = "SELECT d FROM Department d INNER JOIN FETCH d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		System.out.println(lst.size());
		for (Department d : lst) {
			System.out.println(d + " " + d.getEmployees().size());
		}
	}

	@Test
	public void testLeftJoin2() {
		// 左外连接
		String hql = "SELECT DISTINCT d FROM Department d LEFT JOIN d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		System.out.println(lst.size());
		for (Department d : lst) {
			// 普通的左外连接是在使用时才会去发送额外的SELECT语句
			System.out.println(d + " " + d.getEmployees().size());
		}
	}

	@Test
	public void testLeftJoin() {
		// 左外连接
		String hql = "SELECT d FROM Department d LEFT JOIN d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		System.out.println(lst.size());
		lst = new ArrayList<>(new LinkedHashSet<>(lst));
		System.out.println(lst.size());
		for (Department d : lst) {
			// 普通的左外连接是在使用时才会去发送额外的SELECT语句
			System.out.println(d + " " + d.getEmployees().size());
		}
	}

	@Test
	public void testLeftJoinFetch3() {
		// 迫切左外连接
		String hql = "FROM Department d LEFT JOIN FETCH d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		System.out.println(lst.size());
		// 使用HashSet去除重复的元素
		lst = new ArrayList<>(new LinkedHashSet<>(lst));
		System.out.println(lst.size());
		for (Department d : lst) {
			System.out.println(d + " " + d.getEmployees().size());
		}
	}

	@Test
	public void testLeftJoinFetch2() {
		// 迫切左外连接
		// 使用DISTINCT可以去除重复的数据
		String hql = "SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		System.out.println(lst.size());
		for (Department d : lst) {
			System.out.println(d + " " + d.getEmployees().size() + " " + d.getEmployees());
		}
	}

	@Test
	public void testLeftJoinFetch() {
		// 迫切左外连接
		String hql = "FROM Department d LEFT JOIN FETCH d.employees";
		Query query = session.createQuery(hql);
		List<Department> lst = query.list();
		System.out.println(lst.size());
		for (Department d : lst) {
			System.out.println(d + " " + d.getEmployees().size());
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testOtherQuery() {
		// 报表查询,在HQL语句中，比如department_id不直接写department_id,而是写department，由Hibernate负责转换
		String hql = "SELECT min(e.salary) ,max(e.salary) FROM Employee e GROUP BY e.department HAVING min(salary) > :minSalary";
		Query query = session.createQuery(hql);
		query.setInteger("minSalary", 8000);
		List<Object[]> lst = query.list();
		for (Object[] objs : lst)
			System.out.println(Arrays.asList(objs));
	}

	@Test
	public void testFieldQuery2() {
		// 投影查询，可以返回对象的集合
		// 构造器必须已经在Entity中写好
		String hql = "SELECT new Employee(e.name,e.salary,e.email) FROM Employee e WHERE e.department = :department";
		Query query = session.createQuery(hql);
		Department department = new Department();
		department.setId(80L);
		query.setEntity("department", department);
		List<Employee> employees = query.list();
		for (Employee e : employees)
			System.out.println(e);
	}

	@Test
	public void testFieldQuery() {
		// 投影查询，查询部分属性，默认返回Object[]
		String hql = "SELECT e.name,e.salary FROM Employee e WHERE e.department = :department";
		Query query = session.createQuery(hql);
		Department department = new Department();
		department.setId(80L);
		query.setEntity("department", department);
		List<Object[]> employees = query.list();
		for (Object[] obj : employees)
			System.out.println(Arrays.asList(obj));
	}

	@Test
	public void testNamedQuery2() {
		Query query = session.getNamedQuery("getEmployeeBySalaryRange");
		query.setInteger("minSalary", 5000).setInteger("maxSalary", 8000);
		List<Employee> employees = query.list();
		System.out.println(employees.size());
	}

	@Test
	public void testNamedQuery() {
		// 命名查询,方便修改和维护
		Query query = session.getNamedQuery("getEmployeeById");
		query.setLong(0, 12L);
		List<Employee> employees = query.list();
		System.out.println(employees.size());
	}

	@Test
	public void testPageQuery() {
		// 分页查询(MySQL是limit)
		String hql = "FROM Employee";
		Query<Employee> query = session.createQuery(hql, Employee.class);
		// index是从数据库表的第一个ID开始算起，而与数据库中的具体ID无关
		query.setFirstResult(50);
		query.setMaxResults(10);

		List<Employee> employees = query.list();
		for (Employee e : employees) {
			System.out.print(e.getId() + " ");
		}
		System.out.println();
	}

	@Test
	public void testHQLHelloWorld2() {
		// 支持ORDER BY
		String hql = "FROM Employee emp WHERE emp.salary > :salary AND emp.email LIKE :email ORDER BY emp.salary";
		Query query = session.createQuery(hql);
		// 基于命名的参数
		query.setInteger("salary", 5000).setString("email", "%5%");
		List<Employee> employees = query.list();
		System.out.println(employees.size());
	}

	@Test
	public void testHQLHelloWorld() {
		// 注意传入的是department,，不是department_id，在转成SQL时会自动的根据外键设置成department
		// Employee是mapping文件中设定的，不能写为employee
		String hql = "FROM Employee emp WHERE emp.salary > ? AND emp.email LIKE ? AND emp.department = ?";
		Query query = session.createQuery(hql);
		// 基于位置的参数
		// 方法链编程风格
		// 支持实体类类型参数
		Department department = new Department();
		department.setId(93L);
		query.setInteger(0, 5000).setString(1, "%5%").setEntity(2, department);
		List<Employee> employees = query.list();
		System.out.println(employees.size());
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testOne2ManySave() {
		// 生成1端数据
		List<Department> departments = new ArrayList<>();
		for (int i = 0; i < 100; i++)
			departments.add(new Department("DEPT-" + (i < 10 ? "0" : "") + i));

		// 生成N端数据
		List<Employee> employees = new ArrayList<>();
		for (int i = 0; i < 300; i++) {
			// 随机生成邮箱地址
			// StringBuilder是不安全的
			StringBuffer email = new StringBuffer();
			for (int j = 0; j < 5; j++) {
				int t = (int) (Math.random() * 10 + 0);
				email.append(t);
			}
			email.append("@test.com");
			employees.add(new Employee(("EMP-" + (i < 100 ? "0" : "") + (i < 10 ? "0" : "") + i),
					(int) (Math.random() * 8000 + 2000), email.toString()));
		}

		// 随机建立关系
		for (Employee e : employees) {
			int index = (int) (Math.random() * 100);
			if (index % 10 == 0)
				continue;
			Department department = departments.get(index);
			e.setDepartment(department);
		}

		// 先保存1端，再保存n端
		for (Department d : departments)
			session.save(d);
		for (Employee e : employees)
			session.save(e);
	}

}
