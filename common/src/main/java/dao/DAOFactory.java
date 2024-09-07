package dao;

public class DAOFactory {
    public static ManagerDAO getManagerDAO() {
        return JdbiDAOFactory.getManagerDAO();
//        return new ManagerCollectionsDAO();
    }

    public static RequestDAO getRequestDAO() {
        return JdbiDAOFactory.getRequestDAO();
//            return new RequestCollectionsDAO();

    }

    public static TenantDAO getTenantDAO() {
        return JdbiDAOFactory.getTenantDAO();
//            return new TenantCollectionsDAO();
    }

    public static PropertyDAO getPropertyDAO() {
        return JdbiDAOFactory.getPropertyDAO();
    }

    public static ManagementDAO getManagementDAO() {
        return JdbiDAOFactory.getManagementDAO();
    }

}
