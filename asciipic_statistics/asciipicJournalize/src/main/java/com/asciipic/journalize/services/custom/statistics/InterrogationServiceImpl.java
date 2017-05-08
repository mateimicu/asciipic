package com.asciipic.journalize.services.custom.statistics;

import com.asciipic.journalize.connection.DataBaseConnection;
import com.asciipic.journalize.model.InformationJSON;

import java.sql.*;

public class InterrogationServiceImpl implements InterrogationService {
    public int getTotalNumberOfRecordingsFromCustomTable(String table) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        StringBuilder query = new StringBuilder();
        query.append("select count(*) as result from j_").append(table);
        return executeStatement(connection, query.toString());
    }

    @Override
    public int getTheNumberOfRecordingsWithPropertiesFromCustomTable(InformationJSON informationJSON) {
        Connection connection = DataBaseConnection.getInstance().getConnection();
        StringBuilder query = new StringBuilder();
        query.append("select count(*) as result from j_").append(informationJSON.getMainCommand()).append(getWhereCondition(informationJSON));
        return executeStatement(connection, query.toString());
    }

    private int executeStatement(Connection connection, String query) {
        int returnAnswer = 0;
        Statement stmt = null;
        try {
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                returnAnswer = resultSet.getInt("result");
            }
        } catch (SQLException e) {
            System.out.println("SQL error encountered.  \n" + e.getLocalizedMessage());
            returnAnswer = 0;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnAnswer;
    }

    private String getWhereCondition(InformationJSON informationJSON) {
        if (checkIfExistsWhereCondition(informationJSON)) {
            StringBuilder whereCondition = new StringBuilder();
            whereCondition.append(" where '1' = '1'");
            if (!informationJSON.getIp().equals("")) {
                whereCondition.append(" and ip like '").append(informationJSON.getIp()).append("'");
            }
            if (!informationJSON.getTag().equals("")) {
                whereCondition.append(" and tag like '").append(informationJSON.getTag()).append("'");
            }
            if (!informationJSON.getPostDate().equals("")) {
                whereCondition.append(" and (trim(to_char(post_date,'dd-mm-yyyy')) = '").append(informationJSON.getPostDate()).append("'");
                whereCondition.append(" or trim(to_char(post_date,'yyyy-mm-dd')) = '").append(informationJSON.getPostDate()).append("')");
            }
            if (!informationJSON.getPictureSizeHeight().equals("")) {
                whereCondition.append(" and height = ").append(informationJSON.getPictureSizeHeight());
            }
            if (!informationJSON.getPictureSizeWidth().equals("")) {
                whereCondition.append(" and width = ").append(informationJSON.getPictureSizeWidth());
            }
            if (!informationJSON.getUserAgent().equals("")) {
                whereCondition.append(" and upper(user_agent) like '").append(informationJSON.getUserAgent()).append("'");
            }
            if (!informationJSON.getFiltersType().equals("")) {
                whereCondition.append(" and upper(type) like '").append(informationJSON.getFiltersType()).append("'");
            }
            if (!informationJSON.getCauses().equals("")) {
                whereCondition.append(" and upper(cause) like '").append(informationJSON.getCauses()).append("'");

            }
            if (!informationJSON.getTypeOfTime().equals("")) {
                int time = 0;
                if (!informationJSON.getTime().equals("")) {
                    time = Integer.parseInt(informationJSON.getTime());
                }
                if (informationJSON.getTypeOfTime().equals("YEARS")) {
                    time = time * 365;
                } else if (informationJSON.getTypeOfTime().equals("MONTHS")) {
                    time = time * 30;
                }
                whereCondition.append(" and  journalize_id in (select id from journalize where upper(action) like upper('").append(informationJSON.getMainCommand()).append("') and action_date >= sysdate - ").append(time).append(" )");
            }

            return whereCondition.toString();
        }
        return "";
    }

    private boolean checkIfExistsWhereCondition(InformationJSON informationJSON) {
        if (!informationJSON.getIp().equals("")) {
            return true;
        } else if (!informationJSON.getTag().equals("")) {
            return true;
        } else if (!informationJSON.getPostDate().equals("")) {
            return true;
        } else if (!informationJSON.getPictureSizeHeight().equals("")) {
            return true;
        } else if (!informationJSON.getPictureSizeWidth().equals("")) {
            return true;
        } else if (!informationJSON.getUserAgent().equals("")) {
            return true;
        } else if (!informationJSON.getFiltersType().equals("")) {
            return true;
        } else if (!informationJSON.getCauses().equals("")) {
            return true;
        } else if (!informationJSON.getTime().equals("")) {
            return true;
        } else if (!informationJSON.getTypeOfTime().equals("")) {
            return true;
        }
        return false;
    }
}
