package org.hibernate.dialect;

public class MySQL5UTF8InnoDBDialect extends MySQL5InnoDBDialect {
	
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }

}
