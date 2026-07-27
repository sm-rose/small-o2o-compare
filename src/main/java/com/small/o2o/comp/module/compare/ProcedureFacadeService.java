package com.small.o2o.comp.module.compare;


import com.alibaba.excel.metadata.Sheet;
import com.small.o2o.comp.core.enums.MetaBuzTypeEnum;
import com.small.o2o.comp.core.excel.MultipleSheetProperty;
import com.small.o2o.comp.module.compare.base.CommonGenerator;
import com.small.o2o.comp.module.service.oracle.MetaProcedureListService;
import com.small.o2o.comp.module.vo.OracleProcedureVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  xiaocai
 */
@Slf4j
 public class ProcedureFacadeService  extends CommonGenerator {

    @Autowired
    private FilePickService filePickService;
    @Autowired
    private MetaProcedureListService procedureListService ;


    public void doHandle(String filePath) {
        List<MultipleSheetProperty> excelList = getDatas();
        generaterExcel(filePath, excelList);
    }

    public void doHandle2(String filePath) {
        List<MultipleSheetProperty> excelList  = getDatas2();
        generaterExcel(filePath, excelList);
    }

    private List<MultipleSheetProperty>  getDatas() {
        ArrayList<MultipleSheetProperty> excelList = new ArrayList<>();
        for (MetaBuzTypeEnum sheetEnum : MetaBuzTypeEnum.values()) {

             if (9 == sheetEnum.getIndex() || 7 == sheetEnum.getIndex() || 8 == sheetEnum.getIndex()) {
                log.info("开始查 " + sheetEnum.getCode());
                List<OracleProcedureVO> typeList = procedureListService.getProcedureList(sheetEnum.getCode());
                Sheet sheet = new Sheet(sheetEnum.getIndex(), 0);
                sheet.setSheetName(sheetEnum.getDesc());
                MultipleSheetProperty MultipleSheetProperty = new MultipleSheetProperty();
                MultipleSheetProperty.setData(typeList);
                MultipleSheetProperty.setSheet(sheet);
                excelList.add(MultipleSheetProperty);
            }
        }
        return excelList;
    }

    private List<MultipleSheetProperty> getDatas2() {
        ArrayList<MultipleSheetProperty> excelList = new ArrayList<>();

        for (MetaBuzTypeEnum sheetEnum : MetaBuzTypeEnum.values()) {

            if (13 == sheetEnum.getIndex() ) {
                log.info("开始查 " + sheetEnum.getCode());
                List<OracleProcedureVO> typeList = procedureListService.getProcedureList(sheetEnum.getCode());
                Sheet sheet = new Sheet(sheetEnum.getIndex(), 0);
                sheet.setSheetName(sheetEnum.getDesc());
                MultipleSheetProperty MultipleSheetProperty = new MultipleSheetProperty();
                MultipleSheetProperty.setData(typeList);
                MultipleSheetProperty.setSheet(sheet);
                excelList.add(MultipleSheetProperty);
            }

        }

        return excelList;
    }
}
