<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="text/css">
    	body {
			font-size: 12px;
			font-family: "微软雅黑", "宋体"
		}
		.innerTable{
			width:100%;
			border-collapse: collapse;
			border-spacing: 0;
		}
		
		.innerTable tr td:first-child{
			white-space:pre-wrap;
			word-wrap:break-word;
			border-bottom:0px;
			border-left:0px;
		}
		
		.innerTable tr td:not(:first-child){
			white-space:pre-wrap;
			word-wrap:break-word;
			border-bottom:0px;
		}
		.materialtable tr:not(:first-child) td ,
		.npackagtable tr:not(:first-child) td ,
		.wpackagtable tr:not(:first-child) td ,
		.wastagetable tr:not(:first-child) td
		{
			border-top:1px solid #dddddd;
		}
		.price{
			text-align:right;
		}
		.table th {
			font-weight: normal;
			color:#333;
		}
		.table thead th {
			vertical-align: bottom;
		}
        .table-head{
        	text-align:left;
        	white-space:nowrap;
        	width:160px;
        }
        .text{
        	margin:0px 10px;
        }
    	#rowtitle td{
	    	font-size: 13px;
	    	font-weight:bold;
	    	background-color:rgb(0,146,240);
    	}
    	#deductptcostValue td,#nwpackagsubtotalValue td,#subTotal td{
    		color : red;
    	}   	
    	table {
			table-layout: fixed;
			background-color: transparent;
			border-collapse: collapse;
			border-spacing: 0;
		}
		#contrastResult{
			border-bottom:0px;
		}
		#contrastResult .remarks{
			white-space:pre-wrap;
			word-wrap:break-word;
			width:210px;
		}
		#contrastResult .units{
			white-space:pre-wrap;
			word-wrap:break-word;
			width:110px;
		}
		#contrastResult th, #contrastResult td{
			padding:0px;
			vertical-align: middle;	
			white-space:nowrap;
			line-height: 25px;
		}
		.table-bordered {
			border: 1px solid #dddddd;
		}
		.table-bordered th, .table-bordered td {
			border-left: 1px solid #dddddd;
			border-bottom: 1px solid #dddddd;
		}
    </style>
</head>
<body>
	<table id="contrastResult" class="table table-bordered" width=${width} align="center">
	${tableInfo}
	</table>
</body>
</html>