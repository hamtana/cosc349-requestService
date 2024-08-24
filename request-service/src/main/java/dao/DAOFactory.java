package dao;

public class DAOFactory {

        public static RequestDAO getRequestDAO() {
            //return JdbiDAOFactory.getRequestDAO();
            return new RequestCollectionsDAO();

        }

        public static TenantDAO getTenantDAO() {
            //return JdbiDAOFactory.getTenantDAO();
            return new TenantCollectionsDAO();
        }
}
