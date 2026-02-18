(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("exampleDataType");
            this.set_titletext("기본샘플(조회,입력,저장,삭제)");
            this.getSetter("classname").set("Work");
            this.getSetter("inheritanceid").set("");
            if (Form == this.constructor)
            {
                this._setFormPosition(1050,818);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize
            obj = new Dataset("dsList", this);
            obj._setContents("<ColumnInfo><Column id=\"id\" type=\"INT\" size=\"256\"/><Column id=\"stringValue\" type=\"STRING\" size=\"256\"/><Column id=\"intValue\" type=\"INT\" size=\"256\"/><Column id=\"booleanValue\" type=\"INT\" size=\"256\"/><Column id=\"longValue\" type=\"BIGDECIMAL\" size=\"256\"/><Column id=\"floatValue\" type=\"FLOAT\" size=\"256\"/><Column id=\"doubleValue\" type=\"FLOAT\" size=\"256\"/><Column id=\"bigDecimalValue\" type=\"BIGDECIMAL\" size=\"256\"/><Column id=\"dateValue\" type=\"DATE\" size=\"256\"/><Column id=\"timeValue\" type=\"DATETIME\" size=\"256\"/><Column id=\"dateTimeValue\" type=\"DATETIME\" size=\"256\"/><Column id=\"bytesValue\" type=\"BLOB\" size=\"256\"/></ColumnInfo><Rows><Row><Col id=\"id\">1</Col><Col id=\"stringValue\">sample1</Col><Col id=\"intValue\">1</Col><Col id=\"booleanValue\">1</Col><Col id=\"longValue\">1001</Col><Col id=\"floatValue\">1.1</Col><Col id=\"doubleValue\">1.11</Col><Col id=\"bigDecimalValue\">1001.11</Col><Col id=\"dateValue\">20250806092533</Col><Col id=\"dateTimeValue\">20250806092533</Col><Col id=\"bytesValue\">X&apos;89504E470D0A1A0A&apos;</Col><Col id=\"timeValue\">20250806092533987</Col></Row></Rows>");
            this.addChild(obj.name, obj);


            obj = new Dataset("dsSearch", this);
            obj._setContents("<ColumnInfo><Column id=\"title\" type=\"STRING\" size=\"100\"/></ColumnInfo><Rows><Row><Col id=\"title\"/></Row></Rows>");
            this.addChild(obj.name, obj);


            obj = new Dataset("dsDataType", this);
            obj._setContents("<ColumnInfo><Column id=\"id\" type=\"INT\" size=\"256\"/><Column id=\"stringValue\" type=\"STRING\" size=\"256\"/><Column id=\"intValue\" type=\"INT\" size=\"256\"/><Column id=\"booleanValue\" type=\"INT\" size=\"256\"/><Column id=\"longValue\" type=\"BIGDECIMAL\" size=\"256\"/><Column id=\"floatValue\" type=\"FLOAT\" size=\"256\"/><Column id=\"doubleValue\" type=\"FLOAT\" size=\"256\"/><Column id=\"bigDecimalValue\" type=\"BIGDECIMAL\" size=\"256\"/><Column id=\"dateValue\" type=\"DATE\" size=\"256\"/><Column id=\"timeValue\" type=\"TIME\" size=\"256\"/><Column id=\"dateTimeValue\" type=\"DATETIME\" size=\"256\"/><Column id=\"bytesValue\" type=\"BLOB\" size=\"256\"/></ColumnInfo><Rows><Row><Col id=\"id\">1</Col><Col id=\"stringValue\">sample1</Col><Col id=\"intValue\">1</Col><Col id=\"booleanValue\">1</Col><Col id=\"longValue\">1001</Col><Col id=\"floatValue\">1.1</Col><Col id=\"doubleValue\">1.11</Col><Col id=\"bigDecimalValue\">1001.11</Col><Col id=\"dateValue\">20250806092533</Col><Col id=\"dateTimeValue\">20250806092533</Col><Col id=\"bytesValue\">X&apos;89504E470D0A1A0A&apos;</Col><Col id=\"timeValue\">092533</Col></Row></Rows>");
            this.addChild(obj.name, obj);
            
            // UI Components Initialize
            obj = new Grid("Grid00","-2","70",null,null,"2","-5",null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_binddataset("dsList");
            obj.set_autofittype("col");
            obj.getSetter("no").set("true");
            obj._setContents("<Formats><Format id=\"default\"><Columns><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/><Column size=\"80\"/></Columns><Rows><Row size=\"24\" band=\"head\"/><Row size=\"24\"/></Rows><Band id=\"head\"><Cell text=\"ID\"/><Cell col=\"1\" text=\"STRING_VALUE\"/><Cell col=\"2\" text=\"INT_VALUE\"/><Cell col=\"3\" text=\"BOOLEAN_VALUE\"/><Cell col=\"4\" text=\"LONG_VALUE\"/><Cell col=\"5\" text=\"FLOAT_VALUE\"/><Cell col=\"6\" text=\"DOUBLE_VALUE\"/><Cell col=\"7\" text=\"BIG_DECIMAL_VALUE\"/><Cell col=\"8\" text=\"DATE_VALUE\"/><Cell col=\"9\" text=\"TIME_VALUE\"/><Cell col=\"10\" text=\"DATETIME_VALUE\"/><Cell col=\"11\" text=\"BYTES_VALUE\"/></Band><Band id=\"body\"><Cell text=\"bind:id\"/><Cell col=\"1\" text=\"bind:stringValue\" displaytype=\"text\" edittype=\"normal\"/><Cell col=\"2\" text=\"bind:intValue\" edittype=\"normal\"/><Cell col=\"3\" text=\"bind:booleanValue\" edittype=\"normal\"/><Cell col=\"4\" text=\"bind:longValue\" edittype=\"normal\"/><Cell col=\"5\" text=\"bind:floatValue\" edittype=\"normal\"/><Cell col=\"6\" text=\"bind:doubleValue\" edittype=\"normal\"/><Cell col=\"7\" text=\"bind:bigDecimalValue\" edittype=\"normal\" displaytype=\"currency\"/><Cell col=\"8\" text=\"bind:dateValue\" edittype=\"date\" displaytype=\"date\" calendarautoselect=\"true\" calendardateformat=\"yyyy-MM-dd\"/><Cell col=\"9\" edittype=\"normal\" text=\"bind:timeValue\"/><Cell col=\"10\" text=\"bind:dateTimeValue\" edittype=\"date\" displaytype=\"date\" calendareditformat=\"yyyy-MM-dd HH:mm:ss\" calendardateformat=\"yyyy-MM-dd HH:mm:ss\" calendarautoselect=\"true\"/><Cell col=\"11\" edittype=\"none\" displaytype=\"imagecontrol\" text=\"bind:bytesValue\" imagestretch=\"fit\"/></Band></Format></Formats>");
            this.addChild(obj.name, obj);

            obj = new Div("divSearch","0","0",null,"52","0",null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_text("");
            this.addChild(obj.name, obj);

            obj = new Button("btnSearch",null,"5","65",null,"203","22",null,null,null,null,this.divSearch.form);
            obj.set_taborder("1");
            obj.set_text("조회");
            obj.set_borderRadius("5px");
            this.divSearch.addChild(obj.name, obj);

            obj = new Edit("edtTitle","130","5","180","25",null,null,null,null,null,null,this.divSearch.form);
            obj.set_taborder("0");
            this.divSearch.addChild(obj.name, obj);

            obj = new Static("Static01","5","5","125","25",null,null,null,null,null,null,this.divSearch.form);
            obj.set_taborder("2");
            obj.set_text("STRING_VALUE");
            obj.set_cssclass("sta_WF_SubTitle");
            obj.set_padding("0px 0px 0px 20px");
            obj.set_background("url(\'theme://images/img_WF_Treeitem.png\') no-repeat left center");
            this.divSearch.addChild(obj.name, obj);

            obj = new Button("btnAdd",null,"5","65",null,"136","22",null,null,null,null,this.divSearch.form);
            obj.set_taborder("3");
            obj.set_text("추가");
            obj.set_borderRadius("5px");
            this.divSearch.addChild(obj.name, obj);

            obj = new Button("btnDel",null,"5","65",null,"69","22",null,null,null,null,this.divSearch.form);
            obj.set_taborder("4");
            obj.set_text("삭제");
            obj.set_borderRadius("5px");
            this.divSearch.addChild(obj.name, obj);

            obj = new Button("btnSave",null,"5","65","25","2",null,null,null,null,null,this.divSearch.form);
            obj.set_taborder("5");
            obj.set_text("저장");
            obj.set_borderRadius("5px");
            this.divSearch.addChild(obj.name, obj);

            obj = new Button("btnCheck",null,"5","100",null,"btnSearch:12","22",null,null,null,null,this.divSearch.form);
            obj.set_taborder("6");
            obj.set_text("Check 컬럼 타입");
            obj.set_borderRadius("5px");
            this.divSearch.addChild(obj.name, obj);

            obj = new ImageViewer("img00","315","5","85","27",null,null,null,null,null,null,this);
            obj.set_taborder("2");
            obj.set_image("url(\'images::nexacro_5k.png\')");
            obj.set_stretch("fit");
            this.addChild(obj.name, obj);
            // Layout Functions
            //-- Default Layout : this.divSearch.form
            obj = new Layout("default","",0,0,this.divSearch.form,function(p){});
            this.divSearch.form.addLayout(obj.name, obj);

            //-- Default Layout : this
            obj = new Layout("default","",this._adjust_width,this._adjust_height,this,function(p){});
            this.addLayout(obj.name, obj);
            
            // BindItem Information
            obj = new BindItem("item0","img00","text","dsList","bytesValue");
            this.addChild(obj.name, obj);
            obj.bind();
            
            // TriggerItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("exampleDataType.xfdl", function() {

        /***********************************************************************************************
        * FORM EVENT 영역(onload, onbeforeclose)
        /***********************************************************************************************/
        /**
         * @description 화면 onload시 처리내역(필수)
        */
        this.form_onload = function(obj,e)
        {
        	this.gfnFormOnLoad(this);
        };

        /**
         * @description 화면 닫힐때 변경사항 체크(입력 화면에서 변경되는 Dataset 체크 필요, 선택)
         * @return {boolean} false(화면 닫음) / true(화면 닫지 않음)
        */
        this.fnClose = function()
        {
        	if (this.gfnDsIsUpdated(this.dsList)) {
        		return true;
        	}
        	return false;
        };


        /************************************************************************************************
         * CALLBACK 콜백 처리부분(Transaction, Popup)
         ************************************************************************************************/
        /**
         * @description Transaction CallBack 함수(선택)
        */
        this.fnCallback = function(svcID,errorCode,errorMsg)
        {
        	// 에러 시 화면 처리 내역
        	if(errorCode != 0)
        	{
        		return;
        	}

        	switch(svcID)
        	{
        		case "search":
        		case "check":
        			//trace(this.dsList.saveXML());
        			break;

        		case "save":
        			// 저장 되었습니다.
        			this.gfnAlert("msg.save.success");
        			break;
        	}
        };

        /************************************************************************************************
        * CRUD 및 TRANSACTION 서비스 호출 처리
        ************************************************************************************************/
        /**
         * @description 조회
        */
        this.fnSearch = function ()
        {
        	// 조회조건 설정
         	this.dsSearch.setColumn(0, "title"  , this.divSearch.form.edtTitle.value);

         	var strSvcId    = "search";
        	var strSvcUrl   = "select_testDataTypeList.do"; // "retrieve_datalist.do";
        	var inData      = "dsSearch=dsSearch";
        	var outData     = "dsList=output1";
        	var strArg      = "";
        	var callBackFnc = "fnCallback";
        	var isAsync   	= true;

        	this.gfnTransaction(strSvcId , 		// transaction을 구분하기 위한 svc id값
        						strSvcUrl , 	// trabsaction을 요청할 주소
        						inData , 		// 입력값으로 보낼 dataset id , a=b형태로 실제이름과 입력이름을 매칭
        						outData , 		// 처리결과값으로 받을 dataset id, a=b형태로 실제이름과 입력이름을 매칭
        						strArg, 		// 입력값으로 보낼 arguments, strFormData="20120607"
        						callBackFnc, 	// transaction의 결과를 받을 Function 이름
        						isAsync); 		// 비동기통신 여부 [생략가능]
        };
        /**
         * @description 컬럼타입 체크용 tr
        */
        this.divSearch_btnCheck_onclick = function(obj,e)
        {
        	// 조회조건 설정
         	this.dsList.copyData(this.dsDataType);
        	var encodedBytes = nexacro.base64Encode( this.dsList.getColumn(0, "bytesValue") ) ;
        	this.dsList.setColumn(0, "bytesValue", encodedBytes);

         	var strSvcId    = "check";
        	var strSvcUrl   = "check_testDataTypeList.do"; // "retrieve_datalist.do";
        	var inData      = "dsList=dsList:A";
        	var outData     = "dsList=output1";
        	var strArg      = "";
        	var callBackFnc = "fnCallback";
        	var isAsync   	= true;

        	this.gfnTransaction(strSvcId , 		// transaction을 구분하기 위한 svc id값
        						strSvcUrl , 	// trabsaction을 요청할 주소
        						inData , 		// 입력값으로 보낼 dataset id , a=b형태로 실제이름과 입력이름을 매칭
        						outData , 		// 처리결과값으로 받을 dataset id, a=b형태로 실제이름과 입력이름을 매칭
        						strArg, 		// 입력값으로 보낼 arguments, strFormData="20120607"
        						callBackFnc, 	// transaction의 결과를 받을 Function 이름
        						isAsync); 		// 비동기통신 여부 [생략가능]

        };

        /**
         * @description 입력
        */
        this.fnAdd = function()
        {
        	this.dsList.addRow();
        };

        /**
         * @description 삭제
        */
        this.fnDel = function()
        {
        	this.dsList.deleteRow(this.dsList.rowposition);
        };

        /**
         * @description 저장
        */
        this.fnSave = function()
        {
        	// 변경사항 체크
        	if (this.gfnDsIsUpdated(this.dsList) == false) {
        		// 변경된 내역이 없습니다.
        		this.gfnAlert("msg.save.nochange");
        		return;
        	}

        	var strSvcUrl   = "update_deptlist_map.do";  // "update_datalist_map.do";
        	var inData      = "input1=dsList:A";
        	var outData     = "";

        	this.gfnTransaction("save", strSvcUrl, inData, outData);
        };

        /************************************************************************************************
        * 사용자 FUNCTION 영역
        ************************************************************************************************/

        /************************************************************************************************
         * 각 COMPONENT 별 EVENT 영역
        ************************************************************************************************/

        this.divSearch_img00_onclick = function(obj,e)
        {

        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.addEventHandler("onload",this.form_onload,this);
            this.divSearch.form.btnSearch.addEventHandler("onclick",this.fnSearch,this);
            this.divSearch.form.btnAdd.addEventHandler("onclick",this.fnAdd,this);
            this.divSearch.form.btnDel.addEventHandler("onclick",this.fnDel,this);
            this.divSearch.form.btnSave.addEventHandler("onclick",this.fnSave,this);
            this.divSearch.form.btnCheck.addEventHandler("onclick",this.divSearch_btnCheck_onclick,this);
        };
        this.loadIncludeScript("exampleDataType.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
