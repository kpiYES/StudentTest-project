//package com.app.util;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class TransactionWrapper {
//   private DataSource dataSource;
//    public TransactionWrapper(){
//       dataSource = DataSource.getInstance();
//    }
//
//
//   public Connection getTransactionalConnection(){
//       try {
//           Connection connection = getConnection();
//           connection.setAutoCommit(false);
//           return connection;
//       } catch (SQLException e) {
//           throw new RuntimeException(e);
//       }
//   }
//
//   public Connection getConnection(){
//       return dataSource.getConnection();
//   }
//}
