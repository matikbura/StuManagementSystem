package dao;

import domain.Admin;

import java.util.List;
import java.util.Map;
public interface AdminIDao {

	void addAdmin(Admin admin);

	void delAdmin(String loginName);

	void updateAdmin(Admin admin);

	Admin findAdminByLoginName(String loginName);

	Map findAllAdmin(int curPage);

	List findAllAdmin();

	List findAllAdminByMostCon(String LoginName, String name);

	Map findAllAdminByMostCon(String LoginName, String name, int curPage);

	Admin login(String LoginName, String password);
}
