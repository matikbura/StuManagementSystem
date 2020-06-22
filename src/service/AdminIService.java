package service;

import domain.Admin;
import exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface AdminIService {

    void addAdmin(Admin admin) throws ServiceException;

    void delAdmin(String loginName) throws ServiceException;

    void updateAdmin(Admin admin) throws ServiceException;

    Admin findAdminByLoginName(String loginName) throws ServiceException;

    Map findAllAdmin(int curPage) throws ServiceException;

    List findAllAdmin() throws ServiceException;

    List findAllAdminByMostCon(String loginName, String name) throws ServiceException;

    Map findAllAdminByMostCon(String loginName, String name, int curPage) throws ServiceException;

    Admin login(String loginName, String password) throws ServiceException;
}
