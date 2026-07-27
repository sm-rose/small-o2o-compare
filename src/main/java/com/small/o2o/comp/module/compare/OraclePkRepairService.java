package com.small.o2o.comp.module.compare;


import com.alibaba.excel.metadata.Sheet;
import com.small.o2o.comp.core.enums.MetaBuzTypeEnum;
import com.small.o2o.comp.core.excel.MultipleSheetProperty;
import com.small.o2o.comp.module.compare.base.CommonGenerator;
import com.small.o2o.comp.module.service.sql.MetaDbTypeSQLService;
import com.small.o2o.comp.module.vo.ORATablePrimaryKeyVO;
import com.small.o2o.comp.module.vo.ObTablePrimaryKeyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
 public class OraclePkRepairService  extends CommonGenerator {


    @Autowired
    private MetaDbTypeSQLService oracleMetaDataService;
    @Autowired
    private FilePickService filePickService;



    public void listPkList(String filePath){
        List<MultipleSheetProperty> excelList  = getDatas();
        generaterExcel(filePath, excelList);
    }

    private List<MultipleSheetProperty>  getDatas() {
        ArrayList<MultipleSheetProperty> excelList = new ArrayList<>();
        for (MetaBuzTypeEnum sheetEnum : MetaBuzTypeEnum.values()) {

            if (12 == sheetEnum.getIndex()) {
                log.info("开始查 " + sheetEnum.getCode());
                List<ORATablePrimaryKeyVO> typeList = getSomeList(sheetEnum.getCode());
                Sheet sheet = new Sheet(sheetEnum.getIndex(), 0);
                sheet.setSheetName("ORA表主键");
                MultipleSheetProperty MultipleSheetProperty = new MultipleSheetProperty();
                MultipleSheetProperty.setData(typeList);
                MultipleSheetProperty.setSheet(sheet);
                excelList.add(MultipleSheetProperty);
            }
        }
        return excelList;
    }

    private List<ORATablePrimaryKeyVO> getSomeList(String code) {
        List<ORATablePrimaryKeyVO> resultList = new ArrayList<>();
        // 需要处理主键的表
        List<String> tableList = filePickService.getTestOracleTable();

        ORATablePrimaryKeyVO object = null;
        int indexNo = 1;
        for (String tableName : tableList) {
            if (!StringUtils.hasText(tableName)){
                continue;
            }
            List<ObTablePrimaryKeyVO> oraObjList = oracleMetaDataService.queryTablePrimaryKeyVO(tableName);

            for (ObTablePrimaryKeyVO test : oraObjList) {
                object = new ORATablePrimaryKeyVO();
                object.setNo(String.valueOf(indexNo));

                object.setTableName(test.getTableName());
                object.setConstraintName(test.getConstraintName());
                object.setColumnName(test.getColumnName());
                resultList.add(object);
                indexNo++ ;
            }

        }

        return resultList;
    }


}
