package service.Impl;

import dao.AdminIDao;
import dao.Impl.AdminDaoImpl;
import domain.Admin;
import exception.DaoException;
import exception.ServiceException;
import service.AdminIService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminServiceImpl implements AdminIService {
    private AdminIDao adminIDao =new AdminDaoImpl();
    @Override
    public void addAdmin(Admin admin) throws ServiceException, ServiceException, ServiceException {
        try{
            adminIDao.addAdmin(admin);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public void delAdmin(String loginName) throws ServiceException {
        try{
            adminIDao.delAdmin(loginName);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public void updateAdmin(Admin admin) throws ServiceException {
        try{
            adminIDao.updateAdmin(admin);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public Admin findAdminByLoginName(String loginName) throws ServiceException {
        Admin admin=null;
        try{
            admin = adminIDao.findAdminByLoginName(loginName);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return admin;
    }

    @Override
    public Map findAllAdmin(int curPage) throws ServiceException {
        Map map=null;
        try{
            map = adminIDao.findAllAdmin(curPage);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return map;
    }

    @Override
    public List findAllAdmin() throws ServiceException {
        List list=new ArrayList();
        try{
            list = adminIDao.findAllAdmin();
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllAdminByMostCon(String loginName, String name) throws ServiceException {
        List list = null;
        try{
            list = adminIDao.findAllAdminByMostCon(loginName,name);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public Map findAllAdminByMostCon(String loginName, String name, int curPage) throws ServiceException {
        Map map  = null;
        try{
            map = adminIDao.findAllAdminByMostCon(loginName,name,curPage);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return map;
    }

    @Override
    public Admin login(String loginName, String password) throws ServiceException {
        Admin admin = null;
        try{
            admin = adminIDao.login(loginName,password);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return admin;
    }
}
