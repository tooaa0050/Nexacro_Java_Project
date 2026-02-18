(function()
{
    return function()
    {
        if (!this._is_form)
            return;
        
        var obj = null;
        
        this.on_create = function()
        {
            this.set_name("fileReader");
            this.set_titletext("New Form");
            if (Form == this.constructor)
            {
                this._setFormPosition(1280,720);
            }
            
            // Object(Dataset, ExcelExportObject) Initialize
            obj = new Dataset("Dataset00", this);
            obj._setContents("<ColumnInfo><Column id=\"Column0\" type=\"BLOB\" size=\"256\"/></ColumnInfo><Rows><Row/></Rows>");
            this.addChild(obj.name, obj);


            obj = new FileDialog("FileDialog00", this);
            this.addChild(obj.name, obj);


            obj = new VirtualFile("VirtualFile00", this);
            obj.getSetter("onsuccess").set("VirtualFile00_onsuccess");
            obj.getSetter("onerror").set("VirtualFile00_onerror");
            this.addChild(obj.name, obj);
            
            // UI Components Initialize
            obj = new Button("Button00","10","10","120","50",null,null,null,null,null,null,this);
            obj.set_taborder("0");
            obj.set_text("파일선택");
            this.addChild(obj.name, obj);

            obj = new ImageViewer("ImageViewer00","10","70","500","330",null,null,null,null,null,null,this);
            obj.set_taborder("1");
            obj.set_border("2px solid");
            this.addChild(obj.name, obj);

            obj = new ImageViewer("ImageViewer00_00","10.00","413","500","287",null,null,null,null,null,null,this);
            obj.set_taborder("2");
            obj.set_border("2px solid");
            this.addChild(obj.name, obj);

            obj = new Static("sta00","538","94","437","104",null,null,null,null,null,null,this);
            obj.set_taborder("3");
            obj.set_text("<---그냥 이미지에 표현");
            this.addChild(obj.name, obj);

            obj = new Static("sta00_00","530","428","437","104",null,null,null,null,null,null,this);
            obj.set_taborder("4");
            obj.set_text("<---BLOB 데이터 표현");
            this.addChild(obj.name, obj);
            // Layout Functions
            //-- Default Layout : this
            obj = new Layout("default","",1280,720,this,function(p){});
            this.addLayout(obj.name, obj);
            
            // BindItem Information
            obj = new BindItem("item0","ImageViewer00_00","image","Dataset00","Column0");
            this.addChild(obj.name, obj);
            obj.bind();
            
            // TriggerItem Information

        };
        
        this.loadPreloadList = function()
        {

        };
        
        // User Script
        this.registerScript("sampleFleReader.xfdl", function() {

        this.Button00_onclick = function(obj,e)
        {
        	this.FileDialog00.open("Image Upload",  FileDialog.LOAD);
        };

        this.FileDialog00_onclose = function(obj,e)
        {
        	if(nexacro._Browser=="Runtime")
        	{
        		this.ImageViewer00.set_image("URL('file://"+e.virtualfiles[0].fullpath+"')");
        		if(e.reason==1) {
        			var rtn = this.VirtualFile00.open(e.virtualfiles[0].fullpath, VirtualFile.openRead|VirtualFile.openBinary);
        		}
        	} else {
        		this.fnReadImageFile(e.virtualfiles);
        	}

        };

        this.fnReadImageFile = function(filelist)
        {

        	if(filelist)
        	{
        		if ( /\.(jpe?g|png|gif)$/i.test(filelist[0].filename) ) {
        		  var reader = new FileReader();
        		  reader.targetForm = this;
        		  reader.addEventListener("load", function () {
        		  this.targetForm.ImageViewer00.set_image(this.result);
        		  this.targetForm.Dataset00.setColumn(0,"Column0",this.result);
        		  }, false);
        		  reader.readAsDataURL(filelist[0]._handle);
        		}

        	}
        }


        this.VirtualFile00_onerror = function(obj,e)
        {
        	alert(e.errormsg);
        };

        this.VirtualFile00_onsuccess = function(obj,e)
        {
        trace("Vir==" + e.reason);
        	if(e.reason == 1)
        	{
        		obj.read()
        	}
        	else if(e.reason == 3)
        	{
        		//this.ImageViewer00.set_image(e.binarydata);
        		this.Dataset00.setColumn(0,"Column0",e.binarydata);
        	}
        };

        });
        
        // Regist UI Components Event
        this.on_initEvent = function()
        {
            this.Button00.addEventHandler("onclick",this.Button00_onclick,this);
            this.FileDialog00.addEventHandler("onclose",this.FileDialog00_onclose,this);
        };
        this.loadIncludeScript("sampleFleReader.xfdl");
        this.loadPreloadList();
        
        // Remove Reference
        obj = null;
    };
}
)();
