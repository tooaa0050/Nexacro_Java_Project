(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("sample_streaming_video");
            this.set_titletext("New Form");
            if (Form == this.constructor)
            {
                this._setFormPosition(1280,720);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize
            obj = new Dataset("ds_list", this);
            obj._setContents("<ColumnInfo><Column id=\"id\" type=\"STRING\" size=\"256\"/><Column id=\"fileName\" type=\"STRING\" size=\"256\"/><Column id=\"url\" type=\"STRING\" size=\"256\"/></ColumnInfo><Rows><Row><Col id=\"id\">1</Col><Col id=\"fileName\">244839_medium.mp4</Col></Row><Row><Col id=\"id\">2</Col><Col id=\"fileName\">260397_medium.mp4</Col></Row><Row><Col id=\"id\">3</Col><Col id=\"fileName\">4121058-uhd_3840_2160_25fps.mp4</Col></Row><Row><Col id=\"fileName\">file_example_MP4_1920_18MG.mp4</Col><Col id=\"id\">4</Col></Row></Rows>");
            this.addChild(obj.name, obj);
            
            // UI Components Initialize
            obj = new Static("sta00","314","6","596","394",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_text("sta00");
            obj.set_background("aliceblue");
            this.addChild(obj.name, obj);

            obj = new VideoPlayer("vp_video","318","11","586","339",null,null,null,null,null,null,this);
            obj.set_taborder("1");
            this.addChild(obj.name, obj);

            obj = new Grid("grdMedia","10","6","300","394",null,null,null,null,null,null,this);
            obj.set_taborder("2");
            obj.set_binddataset("ds_list");
            obj._setContents("<Formats><Format id=\"default\"><Columns><Column size=\"67\"/><Column size=\"221\"/></Columns><Rows><Row size=\"24\" band=\"head\"/><Row size=\"24\"/></Rows><Band id=\"head\"><Cell text=\"id\"/><Cell col=\"1\" text=\"fileName\"/></Band><Band id=\"body\"><Cell text=\"bind:id\"/><Cell col=\"1\" text=\"bind:fileName\"/></Band></Format></Formats>");
            this.addChild(obj.name, obj);

            obj = new Static("sta01","318","356","588","40",null,null,null,null,null,null,this);
            obj.set_taborder("3");
            obj.set_text("sta01");
            obj.set_background("white");
            this.addChild(obj.name, obj);

            obj = new Button("btn_play","322","362","71","29",null,null,null,null,null,null,this);
            obj.set_taborder("4");
            obj.set_text("Play");
            this.addChild(obj.name, obj);

            obj = new Button("btn_pause","400","362","71","29",null,null,null,null,null,null,this);
            obj.set_taborder("5");
            obj.set_text("Pause");
            this.addChild(obj.name, obj);

            obj = new Button("btn_stop","478","362","71","29",null,null,null,null,null,null,this);
            obj.set_taborder("6");
            obj.set_text("Stop");
            this.addChild(obj.name, obj);

            obj = new Button("btn_forward","556","362","71","29",null,null,null,null,null,null,this);
            obj.set_taborder("7");
            obj.set_text("Forward");
            this.addChild(obj.name, obj);

            obj = new Button("btn_rewind","634","362","71","29",null,null,null,null,null,null,this);
            obj.set_taborder("8");
            obj.set_text("Rewind");
            this.addChild(obj.name, obj);

            obj = new Static("st_playtime","742","368","120","22",null,null,null,null,null,null,this);
            obj.set_taborder("9");
            obj.set_text("sta02");
            this.addChild(obj.name, obj);

            obj = new Static("sta00_00","10","407","894","23",null,null,null,null,null,null,this);
            obj.set_taborder("10");
            obj.set_text("ㅇ uiadapter-jakarta-core 1.0.18-SNAPSHOT 이후 버전 지원");
            obj.set_visible("true");
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
        this.registerScript("sample_streaming_video.xfdl", function() {

        this.grdMedia_oncelldblclick = function(obj,e)
        {
        	var row = e.row;
        	var fileName = this.ds_list.getColumn(row, "fileName");
        	var url = "http://localhost:8080/uiadapter/streamingVideo.do?fileName="+fileName+"&streamType=nio";
        	this.vp_video.set_url(url);
        	this.vp_video.play();
        };

        this.btn_play_onclick = function(obj,e)
        {
        	this.vp_video.play();
        };

        this.btn_pause_onclick = function(obj,e)
        {
        	this.vp_video.pause();
        };

        this.btn_stop_onclick = function(obj,e)
        {
        	this.vp_video.stop();
        };

        this.btn_forward_onclick = function(obj,e)
        {
        	this.vp_video.set_currenttime(this.vp_video.currenttime + 2000);
        };

        this.btn_rewind_onclick = function(obj,e)
        {
        	this.vp_video.set_currenttime(this.vp_video.currenttime - 2000);
        };

        this.vp_video_oncurrenttimechanged = function(obj,e)
        {
        	var strPlayTime = nexacro.round(this.vp_video.currenttime / 1000) + " / " + nexacro.round(this.vp_video.duration / 1000);

        	this.st_playtime.set_text(strPlayTime);
        };

        this.vp_video_onplaystatuschanged = function(obj,e)
        {
        	switch(e.newstate)
        	{
        		case "stop":
        				this.st_playtime.set_text(" ");
        		case "pause":
        		case "play":
        		case "buffer":
        		case "ended":
        		default:
        			trace("state: "+ e.newstate);
        	}
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.vp_video.addEventHandler("oncurrenttimechanged",this.vp_video_oncurrenttimechanged,this);
            this.vp_video.addEventHandler("onplaystatuschanged",this.vp_video_onplaystatuschanged,this);
            this.grdMedia.addEventHandler("oncelldblclick",this.grdMedia_oncelldblclick,this);
            this.btn_play.addEventHandler("onclick",this.btn_play_onclick,this);
            this.btn_pause.addEventHandler("onclick",this.btn_pause_onclick,this);
            this.btn_stop.addEventHandler("onclick",this.btn_stop_onclick,this);
            this.btn_forward.addEventHandler("onclick",this.btn_forward_onclick,this);
            this.btn_rewind.addEventHandler("onclick",this.btn_rewind_onclick,this);
        };
        this.loadIncludeScript("sample_streaming_video.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
