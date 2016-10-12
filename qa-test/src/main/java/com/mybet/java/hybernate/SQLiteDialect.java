package com.mybet.java.hybernate;

import java.sql.Types;

import org.hibernate.dialect.Dialect;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(Types.BIT, "INTEGER");
        registerColumnType(Types.TINYINT, "TINYINT");
        registerColumnType(Types.SMALLINT, "SMALLINT");
        registerColumnType(Types.INTEGER, "INTEGER");
        registerColumnType(Types.BIGINT, "BIGINT");
        registerColumnType(Types.FLOAT, "FLOAT");
        registerColumnType(Types.REAL, "REAL");
        registerColumnType(Types.DOUBLE, "DOUBLE");
        registerColumnType(Types.NUMERIC, "NUMERIC");
        registerColumnType(Types.DECIMAL, "DECIMAL");
        registerColumnType(Types.CHAR, "CHAR");
        registerColumnType(Types.VARCHAR, "VARCHAR");
        registerColumnType(Types.LONGVARCHAR, "LONGVARCHAR");
        registerColumnType(Types.DATE, "DATE");
        registerColumnType(Types.TIME, "TIME");
        registerColumnType(Types.TIMESTAMP, "TIMESTAMP");
        registerColumnType(Types.BINARY, "BLOB");
        registerColumnType(Types.VARBINARY, "BLOB");
        registerColumnType(Types.LONGVARBINARY, "BLOB");
        registerColumnType(Types.BLOB, "BLOB");
        registerColumnType(Types.CLOB, "BLOB");
        registerColumnType(Types.BOOLEAN, "INTEGER");
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    @Override
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    @Override
    public String getCurrentTimestampSelectString() {
        return "SELECT current_timestamp";
    }

    @Override
    public boolean supportsUnionAll() {
        return true;
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }

    @Override
    public boolean dropConstraints() {
        return false;
    }

    @Override
    public String getAddColumnString() {
        return "ADD COLUMN";
    }

    @Override
    public String getForUpdateString() {
        return "";
    }

    @Override
    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    @Override
    public boolean supportsCascadeDelete() {
        return false;
    }
}
