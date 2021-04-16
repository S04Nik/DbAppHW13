package com.example.DbAppHW13;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver"); // определяет драйвер СУБД
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3308/pa812",
                "root","root");
        Statement statement=connection.createStatement();// позволяет выполнять запросы в BD

        // заполнение
//        insert(statement,new Country("Ukraine","1030010","Kiev"),
//                new City("Zhytomyr","Amazing city ",1));
//        insert(statement,new Country("USA","1002010","Washington"),
//                new City("NY","Very big city ",2));
//        insert(statement,new Country("France","1001004","Paris"),
//                new City("Nica","Marvelous city !",3));
        selectAll(statement);
        selectCountryCities(statement,"USA");
        selectCapitals(statement);
        selectCountryCapital(statement,"USA");

    }
    public static void insert(Statement statement,Country country,City city) throws SQLException {
        statement.executeUpdate("insert into `countries` (name,peopleCount,capitalName)" +
                "values ('"+country.name+"','"+country.people+"','"+country.capital+"')");
        statement.executeUpdate("insert into `cities` (name,info,countryId)" +
                "values ('"+city.name+"','"+city.info+"','"+city.countryId+"')");
    }
    public static void selectAll(Statement statement) throws SQLException {
        ResultSet resultSet= statement.executeQuery("select * from countries join cities" +
                " where cities.countryId=countries.idcountries");
        show(resultSet);
    }
    public static void selectCountryCities(Statement statement,String name) throws SQLException {
        System.out.println("-------------------------> CITIES");
        ResultSet resultSet= statement.executeQuery("select cities.name from countries join cities " +
                "where countries.name='"+name+"' and cities.countryId=countries.idcountries ");
        while(resultSet.next()){
            System.out.println("City : "+resultSet.getString(1));
            System.out.println("------------------------->");
        }
    }
    public static void selectCapitals(Statement statement) throws SQLException {
        System.out.println("-------------------------> CAPITALS");
        ResultSet resultSet= statement.executeQuery("select countries.capitalName from countries ");
        while(resultSet.next()){
            System.out.println("Capital : "+resultSet.getString(1));
            System.out.println("------------------------->");
        }
    }
    public static void selectCountryCapital(Statement statement,String name) throws SQLException {
        System.out.println("-------------------------> COUNTRY CAPITAL");
        ResultSet resultSet= statement.executeQuery("select countries.capitalName " +
                "from countries where countries.name='"+name+"'");
        while(resultSet.next()){
            System.out.println("Capital : "+resultSet.getString(1));
            System.out.println("------------------------->");
        }
    }
    private static void show(ResultSet resultSet) throws SQLException {
        while(resultSet.next()){
            System.out.println("Country : "+resultSet.getString(2));
            System.out.println("Number of People : "+resultSet.getString(3));
            System.out.println("Capital :"+resultSet.getString(4));
            System.out.println("Big City : "+resultSet.getString(6));
            System.out.println("Info : "+resultSet.getString(7));
            System.out.println("------------------------->");
        }
    }


}
