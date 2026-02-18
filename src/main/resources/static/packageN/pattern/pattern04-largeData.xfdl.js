(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("Sample004");
            this.set_titletext("대용량 분할전송");
            this.getSetter("classname").set("Work");
            this.getSetter("inheritanceid").set("");
            if (Form == this.constructor)
            {
                this._setFormPosition(1050,818);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize
            obj = new Dataset("dsList", this);
            obj.set_progressload("true");
            obj._setContents("<ColumnInfo><Column id=\"LARGE_ID\" type=\"STRING\" size=\"256\"/><Column id=\"NAME\" type=\"STRING\" size=\"256\"/><Column id=\"REG_DATE\" type=\"DATETIME\" size=\"256\"/><Column id=\"STORY\" type=\"STRING\" size=\"256\"/><Column id=\"STATUS\" type=\"STRING\" size=\"256\"/></ColumnInfo>");
            this.addChild(obj.name, obj);
            
            // UI Components Initialize
            obj = new Grid("Grid00","0","57",null,"444","0",null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_binddataset("dsList");
            obj.set_autofittype("col");
            obj.getSetter("no").set("true");
            obj._setContents("<Formats><Format id=\"default\"><Columns><Column size=\"40\"/><Column size=\"80\"/><Column size=\"100\"/><Column size=\"300\"/><Column size=\"40\"/></Columns><Rows><Row size=\"24\" band=\"head\"/><Row size=\"24\"/></Rows><Band id=\"head\"><Cell text=\"LARGE_ID\"/><Cell col=\"1\" text=\"NAME\"/><Cell col=\"2\" text=\"REG_DATE\"/><Cell col=\"3\" text=\"STORY\"/><Cell col=\"4\" text=\"STATUS\"/></Band><Band id=\"body\"><Cell text=\"bind:LARGE_ID\"/><Cell col=\"1\" text=\"bind:NAME\"/><Cell col=\"2\" text=\"bind:REG_DATE\"/><Cell col=\"3\" text=\"bind:STORY\"/><Cell col=\"4\" text=\"bind:STATUS\"/></Band></Format></Formats>");
            this.addChild(obj.name, obj);

            obj = new Div("divSearch","0","0",null,"52","0",null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_text("");
            this.addChild(obj.name, obj);

            obj = new Button("btnSearch",null,"9","89","34","5",null,null,null,null,null,this.divSearch.form);
            obj.set_taborder("1");
            obj.set_text("조회");
            this.divSearch.addChild(obj.name, obj);
            // Layout Functions
            //-- Default Layout : this.divSearch.form
            obj = new Layout("default","",0,0,this.divSearch.form,function(p){});
            this.divSearch.form.addLayout(obj.name, obj);

            //-- Default Layout : this
            obj = new Layout("default","",1050,818,this,function(p){});
            this.addLayout(obj.name, obj);
            
            // BindItem Information

            
            // TriggerItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("pattern04-largeData.xfdl", function() {
        /**
        *  컨설팅 표준화 작업
        *  @MenuPath    샘플 > transaction
        *  @FileName 	Sample001_transaction.xfdl
        *  @Creator 	soojeong
        *  @CreateDate 	2017.03.09
        *  @Desction         스크립트 표준 및 주석 표준 정의
        ************** 소스 수정 이력 ***********************************************
        *  date          		Modifier                Description
        *******************************************************************************
        *  2017.03.09     	soojeong 	           최초 생성
        *  2017.10.17     	kyk       	           주석 정비
        *******************************************************************************
        */

        /************************************************************************************************
         * FORM 변수 선언 영역
        ************************************************************************************************/


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
        this.cnt = 0;
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
        			trace("["+(this.cnt++)+"::]"+this.dsList.getCount());
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

         	var strSvcId    = "search";
        	var strSvcUrl   = "sampleLargeData.do";
        	var inData      = "";
        	var outData     = "dsList=ds_largeData";
        	var strArg      = "firstRowCount=1000 chunkSize=1000";
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

        /************************************************************************************************
        * 사용자 FUNCTION 영역
        ************************************************************************************************/


        /************************************************************************************************
         * 각 COMPONENT 별 EVENT 영역
        ************************************************************************************************/

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.addEventHandler("onload",this.form_onload,this);
            this.divSearch.form.btnSearch.addEventHandler("onclick",this.fnSearch,this);
        };
        this.loadIncludeScript("pattern04-largeData.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
