(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("cccc");
            this.set_titletext("2줄 엑셀 Import");
            if (Form == this.constructor)
            {
                this._setFormPosition(1280,720);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize
            obj = new Dataset("dsList2", this);
            obj.set_useclientlayout("false");
            obj._setContents("<ColumnInfo><Column id=\"Column0\" type=\"STRING\" size=\"256\"/><Column id=\"Column1\" type=\"STRING\" size=\"256\"/><Column id=\"Column2\" type=\"STRING\" size=\"256\"/><Column id=\"Column3\" type=\"STRING\" size=\"256\"/><Column id=\"Column4\" type=\"STRING\" size=\"256\"/></ColumnInfo><Rows><Row><Col id=\"Column0\">AAA</Col><Col id=\"Column1\">HHH</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">123</Col><Col id=\"Column4\">가나다라</Col></Row><Row><Col id=\"Column0\">BBB</Col><Col id=\"Column1\">III</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">234</Col><Col id=\"Column4\">가다라마</Col></Row><Row><Col id=\"Column0\">CCC</Col><Col id=\"Column1\">JJJ</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">345</Col><Col id=\"Column4\">가라마바</Col></Row><Row><Col id=\"Column0\">DDD</Col><Col id=\"Column1\">KKK</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">456</Col><Col id=\"Column4\">나다라마</Col></Row><Row><Col id=\"Column0\">EEE</Col><Col id=\"Column1\">LLL</Col><Col id=\"Column2\">bbbb</Col><Col id=\"Column3\">567</Col><Col id=\"Column4\">나라마하</Col></Row><Row><Col id=\"Column0\">FFF</Col><Col id=\"Column1\">MMM</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">345</Col><Col id=\"Column4\">카타파하</Col></Row><Row><Col id=\"Column0\">GGG</Col><Col id=\"Column1\">NNN</Col><Col id=\"Column2\">cccc</Col><Col id=\"Column3\">765</Col><Col id=\"Column4\">차타파하</Col></Row><Row><Col id=\"Column0\">AAA</Col><Col id=\"Column1\">HHH</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">123</Col><Col id=\"Column4\">가나다라</Col></Row><Row><Col id=\"Column0\">BBB</Col><Col id=\"Column1\">III</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">234</Col><Col id=\"Column4\">가다라마</Col></Row><Row><Col id=\"Column0\">CCC</Col><Col id=\"Column1\">JJJ</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">345</Col><Col id=\"Column4\">가라마바</Col></Row><Row><Col id=\"Column0\">DDD</Col><Col id=\"Column1\">KKK</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">456</Col><Col id=\"Column4\">나다라마</Col></Row><Row><Col id=\"Column0\">EEE</Col><Col id=\"Column1\">LLL</Col><Col id=\"Column2\">bbbb</Col><Col id=\"Column3\">567</Col><Col id=\"Column4\">나라마하</Col></Row><Row><Col id=\"Column0\">FFF</Col><Col id=\"Column1\">MMM</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">345</Col><Col id=\"Column4\">카타파하</Col></Row><Row><Col id=\"Column0\">GGG</Col><Col id=\"Column1\">NNN</Col><Col id=\"Column2\">cccc</Col><Col id=\"Column3\">765</Col><Col id=\"Column4\">차타파하</Col></Row><Row><Col id=\"Column0\">AAA</Col><Col id=\"Column1\">HHH</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">123</Col><Col id=\"Column4\">가나다라</Col></Row><Row><Col id=\"Column0\">BBB</Col><Col id=\"Column1\">III</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">234</Col><Col id=\"Column4\">가다라마</Col></Row><Row><Col id=\"Column0\">CCC</Col><Col id=\"Column1\">JJJ</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">345</Col><Col id=\"Column4\">가라마바</Col></Row><Row><Col id=\"Column0\">DDD</Col><Col id=\"Column1\">KKK</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">456</Col><Col id=\"Column4\">나다라마</Col></Row><Row><Col id=\"Column0\">EEE</Col><Col id=\"Column1\">LLL</Col><Col id=\"Column2\">bbbb</Col><Col id=\"Column3\">567</Col><Col id=\"Column4\">나라마하</Col></Row><Row><Col id=\"Column0\">FFF</Col><Col id=\"Column1\">MMM</Col><Col id=\"Column2\">aaaa</Col><Col id=\"Column3\">345</Col><Col id=\"Column4\">카타파하</Col></Row><Row><Col id=\"Column0\">GGG</Col><Col id=\"Column1\">NNN</Col><Col id=\"Column2\">cccc</Col><Col id=\"Column3\">765</Col><Col id=\"Column4\">차타파하</Col></Row></Rows>");
            this.addChild(obj.name, obj);


            obj = new Dataset("dsImport", this);
            obj._setContents("");
            this.addChild(obj.name, obj);
            
            // UI Components Initialize
            obj = new Button("btnClear",null,"30","83","28","289",null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_text("clear");
            this.addChild(obj.name, obj);

            obj = new Button("btnExcelImport",null,"30","163","28","118",null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_text("엑셀불러오기");
            this.addChild(obj.name, obj);

            obj = new Button("Button00",null,"66","252","28","120",null,null,null,null,null,this);
            obj.set_taborder("2");
            obj.set_text("Export");
            this.addChild(obj.name, obj);

            obj = new Grid("Grid00","62","50","770","424",null,null,null,null,null,null,this);
            obj.set_taborder("3");
            obj.set_binddataset("dsImport");
            obj._setContents("<Formats><Format id=\"default\"><Columns><Column size=\"200\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/></Columns><Rows><Row size=\"24\" band=\"head\"/><Row size=\"24\"/></Rows><Band id=\"head\"><Cell/><Cell col=\"1\"/><Cell col=\"2\"/><Cell col=\"3\"/><Cell col=\"4\"/></Band><Band id=\"body\"><Cell text=\"bind:Column0\"/><Cell col=\"1\" text=\"bind:Column1\"/><Cell col=\"2\" text=\"bind:Column2\"/><Cell col=\"3\" text=\"bind:Column3\"/><Cell col=\"4\" text=\"bind:Column4\"/></Band></Format></Formats>");
            this.addChild(obj.name, obj);
            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","",1280,720,this,function(p){});
            this.addLayout(obj.name, obj);
            
            // BindItem Information

            
            // TriggerItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("Excel_Import_Multi_Row_N.xfdl", function() {
        this.url = "http://localhost:8080/XExportImport.do";

        this.btnExcelImport_onclick = function(obj,e)
        {
        	//this.gfnExcelImportAll("dsList","sheet1","A1:E1","A2","fnImportCallback","import",this);
        	//this.gfnExcelImportAll("dsImport", "importId_grd03", this, "sheet1", "A1");
        	//this.gfnExcelImport("dsImport", "importId_grd03", this, "sheet1", "A1");
        	this.fnExcelImport("dsImport","sheet1","A1","fnImportCallback","import",this);
        };

        /**
         * @class  excel import( 데이터 헤더제외 ) <br>
         * @param {String} sDataset - dataset
         * @param {String} [sSheet]	- sheet name
         * @param {String} [sBody] - body 영역지정
         * @param {String} [sCallback] - callback 함수
         * @param {String} [sImportId] - import id(callback호출시 필수)
         * @param {Object} [objForm] - form object(callback호출시 필수)
         * @return N/A
         * @example
         * this.gfnExcelImport("dsList","SheetName","A2","fnImportCallback","import",this);
         */
        this.fnExcelImport = function(sDataset,sSheet,sBody,sCallback, sImportId, objForm)
        {
        	this.setWaitCursor(true);

        	if(this.gfnIsNull(sSheet)) sSheet = "sheet1";
        	if(this.gfnIsNull(sBody)) sBody = "A2";

        	var svcUrl = this.url; //"svcurl::XExportImport.do";

        	var objImport ;
        	objImport = new nexacro.ExcelImportObject(sDataset+"_ExcelImport",this);
        	objImport.set_importurl(svcUrl);
        	objImport.set_importtype(nexacro.ImportTypes.EXCEL);
        	objImport.outds = sDataset;

        	if (!this.gfnIsNull(sCallback))
        	{
        		objImport.callback = sCallback;
        		objImport.importid = sImportId;
        		objImport.form = objForm;
        	}

        	//out dataset 생성(차후 onsucess 함수에서 헤더생성하기 위한)
        	var sOutDsName = sDataset+"_outds";
        	if(this.isValidObject(sOutDsName)) this.removeChild(sOutDsName);
        	var objOutDs = new Dataset();
        	objOutDs.name = sOutDsName;
        	this.addChild(objOutDs.name, objOutDs);

        	objImport.addEventHandler("onsuccess", this.gfnImportOnsuccess, this);
        	objImport.addEventHandler("onerror", this.gfnImportAllOnerror, this);
        	var sParam = "[command=getsheetdata;output=outDs;body=" + sSheet + "!" + sBody +";]";
         	var sParam2 = "[" + sOutDsName + "=outDs]";

        	objImport.importData("", sParam, sParam2);
        	objImport = null;
        };

        /*
        this.gfnExcelImport2 = function(sDataset, sImportId, objForm, sSheet, sBody, sCallback)
        {
        	//this.setWaitCursor(true);

        	objForm = this;
        	sSheet = "Test";
        	sBody = "A2";
        	sCallback = "fnImportCallback";

        	var objImport;
        	objImport = new nexacro.ExcelImportObject(sDataset+"_ExcelImport",this);
        	objImport.set_importurl(this.url);
        	objImport.set_importtype(nexacro.ImportTypes.CSV);
        	objImport.outds = sDataset;  // 데이타셋명

        	objImport.callback = sCallback;
        	objImport.importid = sImportId;
        	objImport.form = objForm;

         	//out dataset 생성(차후 onsucess 함수에서 헤더생성하기 위한)
         	var sOutDsName = sDataset+"_outds";
        	if(this.isValidObject(sOutDsName)) this.removeChild(sOutDsName);
        	var objOutDs = new Dataset();
        	objOutDs.name = sOutDsName;
        	this.addChild(objOutDs.name, objOutDs);
        //this.gfnDsPrn(objOutDs);

        	// 이벤트생성.
        	objImport.addEventHandler("onsuccess", this.gfnImportOnsuccess, this);
        	objImport.addEventHandler("onerror", this.gfnImportOnerror, this);

        	// "Head" 항목에 정의된 영역은 Import 시 DataSet 의 Column 명으로 사용되는데  "Head" 항목 생략 시 "Column0", "Column1" ... 으로 자동설정됩니다.
        	var strRange = "[Command=getsheetdata;Output=outDs;Body=Sheet2!B3]"; //[command=getsheetdata;output=outDs;body=!A2;]
        	                                                                                        //[Command=getsheetdata;Output=output1;Head=!A1:K1;Body=!A2:K15]
         	var strOutDatasets = "[" + sOutDsName + "=outDs]";

        	// 엑셀을 DataSet으로 Import 하는 메소드입니다
        	objImport.importData("", strRange, strOutDatasets);
        	objImport = null;

        };
        */

        this.gfnImportOnsuccess = function(obj,  e)
        {
        	trace("엑셀import : 성공....");
        	//alert(e.url);
        	//this.setWaitCursor(false);

        	var objImportDs = this.objects[obj.outds+"_outds"];

        	trace("objImportDs---> " + objImportDs.saveXML());
        	for(var i=0; i<objImportDs.rowcount; i++)
        	{
        		if (i % 2 == 0)
        		{
        			var nRow = this.dsList2.addRow();
        			this.dsList2.setColumn(nRow, "Column0", objImportDs.getColumn(i, "Column0"));
        			this.dsList2.setColumn(nRow, "Column1", objImportDs.getColumn(i, "Column1"));
        			this.dsList2.setColumn(nRow, "Column2", objImportDs.getColumn(i, "Column2"));
        		}
        		else
        		{
        			this.dsList2.setColumn(nRow, "Column3", objImportDs.getColumn(i, "Column0"));
        			this.dsList2.setColumn(nRow, "Column4", objImportDs.getColumn(i, "Column2"));
        		}
        	}


        	var objImportDs = this.objects[obj.outds+"_outds"]; // Column0, Column1 ...칼럼명과 데이타가 있는 데이타셋.
        	var objOrgDs = this.objects[obj.outds];             // 제대로된 칼럼명만 갖는 데이타셋.(데이타는 없음)
        	var sCallback = obj.callback;
        	var sImportId = obj.importid;
        	var objForm = obj.form;
        	var sColumnId;
        // this.gfnDsPrn(objImportDs);
        // this.gfnDsPrn(objOrgDs);

        	//기존 데이터셋의 내용으로 헤더복사
        	for (var i=0; i<objOrgDs.getColCount(); i++)
        	{
        		sColumnId = "Column"+i;
        		if (sColumnId != objOrgDs.getColID(i))
        		{
        			objImportDs.updateColID(sColumnId, objOrgDs.getColID(i))
        		}
        	}

        	objOrgDs.clearData();
        	objOrgDs.copyData(objImportDs);
        //this.gfnDsPrn(objOrgDs);

        	//화면의 callback 함수 호출
        // 	if (!this.gfnIsNull(sCallback)) {
        // 		this.lookupFunc(sCallback).call(this, sImportId);
        // 	}


        };


        this.gfnImportOnerror = function(obj,  e)
        {
        	trace("엑셀import : 실패");
        	trace("obj.name=["+obj.name+ "], e.eventid=["+e.eventid+"]");
        	trace("e.eventid: " + e.eventid);
        	trace("e.fromobject: " + e.fromobject);
        	console.log(e.fromobject);
        	trace("e.fromreferenceobject: " + e.fromreferenceobject);
        	console.log(e.fromreferenceobject);
        	trace("e.errorcode: " +  e.errorcode);
        	trace("e.errormsg: " + e.errormsg);

        	//this.setWaitCursor(false);
        };
        this.btnClear_onclick = function(obj,e)
        {
        	this.dsList2.clearData();
        };

        this.Button00_onclick = function(obj,e)
        {
        	this.ExcelExportObject00 = new ExcelExportObject();

        	this.ExcelExportObject00.addEventHandler("onprogress", this.ExcelExportObject00_onprogress, this);
        	this.ExcelExportObject00.addEventHandler("onsuccess", this.ExcelExportObject00_onsuccess, this);
        	this.ExcelExportObject00.addEventHandler("onerror", this.ExcelExportObject00_onerror, this);

        	var ret = this.ExcelExportObject00.addExportItem(nexacro.ExportItemTypes.GRID, this.grd03, "Sheet1!A1","allband","allrecord","suppress","allstyle");

        	this.ExcelExportObject00.set_exportmessageprocess("%d [ %d / %d ]");
        	this.ExcelExportObject00.set_exportuitype("exportprogress");
        	this.ExcelExportObject00.set_exporteventtype("itemrecord");
            this.ExcelExportObject00.set_exporttype(nexacro.ExportTypes.EXCEL2007);


        	this.ExcelExportObject00.set_exportfilename("ExcelExport_Sample");
            this.ExcelExportObject00.set_exporturl(this.url);

        	this.ExcelExportObject00.exportDataEx();
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.btnClear.addEventHandler("onclick",this.btnClear_onclick,this);
            this.btnExcelImport.addEventHandler("onclick",this.btnExcelImport_onclick,this);
            this.Button00.addEventHandler("onclick",this.Button00_onclick,this);
        };
        this.loadIncludeScript("Excel_Import_Multi_Row_N.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
