package com.training.carsharing.dao.util;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.Locale;

public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

/*    @Override
    public String foreignKeyColumnName(final String propertyName, final String propertyEntityName, final String propertyTableName,
                                       final String referencedColumnName) {
        final String base = super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName);
//        addUnderscores(base);
        return ((base != null) && (base.length() > 0)) ? base + "_id" : base;
    }

    @Override
    public String classToTableName(String className) {
        final String s = addUnderscores(StringHelper.unqualify(className));
        String tableName = s.substring(0, 1).toUpperCase() + s.substring(1);
        System.out.println("tableName "+tableName);
        return tableName;
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        final String s = addUnderscores(StringHelper.unqualify(propertyName));
        String columnName = s.substring(0, 1).toUpperCase() + s.substring(1);
        System.out.println("columnName"+columnName);
        return columnName;
    }*/

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(addUnderscores(name.toString()));
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(addUnderscores(name.toString()));
    }
    protected static String addUnderscores(String name) {
        StringBuilder buf = new StringBuilder( name.replace('.', '_') );
        for (int i=1; i<buf.length()-1; i++) {
            if (
                    Character.isLowerCase( buf.charAt(i-1) ) &&
                            Character.isUpperCase( buf.charAt(i) ) &&
                            Character.isLowerCase( buf.charAt(i+1) )
                    ) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase(Locale.ROOT);
    }
}
