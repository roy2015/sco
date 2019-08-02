<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<head>
		<style type="text/css" media="screen" type="text/css">
	    	body {
			  font-size: 12px;
			  font-family: "微软雅黑", "宋体";
			}
			/*table样式*/
				
			.table-bordered {
				border: 1px solid #ddd;
				border-collapse: separate;
				border-left: 0;
				-webkit-border-radius: 4px;
				-moz-border-radius: 4px;
				border-radius: 4px;
			}
			.table {
				width: 400%;
				/* margin-bottom: 20px; */
			}
			table {
				max-width: 400%;
				background-color: transparent;
				border-collapse: collapse;
				border-spacing: 0;
				font-size: 12px;
			}
			table {
				display: table;
				border-collapse: separate;
				border-spacing: 0px;
				border-color: gray;
			}
			.table-bordered thead:first-child tr:first-child>th:first-child, .table-bordered tbody:first-child tr:first-child>td:first-child, .table-bordered tbody:first-child tr:first-child>th:first-child {
				-webkit-border-top-left-radius: 4px;
				border-top-left-radius: 4px;
				-moz-border-radius-topleft: 4px;
			}
			.table-bordered caption+thead tr:first-child th, .table-bordered caption+tbody tr:first-child th, .table-bordered caption+tbody tr:first-child td, .table-bordered colgroup+thead tr:first-child th, .table-bordered colgroup+tbody tr:first-child th, .table-bordered colgroup+tbody tr:first-child td, .table-bordered thead:first-child tr:first-child th, .table-bordered tbody:first-child tr:first-child th, .table-bordered tbody:first-child tr:first-child td {
				border-top: 0;
			}
			.table caption+thead tr:first-child th, .table caption+thead tr:first-child td, .table colgroup+thead tr:first-child th, .table colgroup+thead tr:first-child td, .table thead:first-child tr:first-child th, .table thead:first-child tr:first-child td {
				border-top: 0;
			}
			.table thead th {
				vertical-align: bottom;
			}
			.table-bordered th, .table-bordered td {
				border-left: 1px solid #ddd;
			}
			.table th {
				font-weight: bold;
				background-color:#ececec;
			}
			.table th, .table td {
				padding: 3px;
				line-height: 1.5;
				text-align: left;
				vertical-align: top;
				border-top: 1px solid #ddd;
				font-size:13px
				white-space: pre-wrap;
				word-break:break-all; /*支持IE，chrome，FF不支持*/
				word-wrap:break-word;
			}
			th {
				font-weight: bold;
			}
			td, th {
				display: table-cell;
				vertical-align: inherit;
			}
			.table td input[type="text"]{width:220px;}
	    </style>
		</head>
	<body>
		<table  class="table table-bordered" style="width:700px">
			${tableInfo}
		</table>
	</body>
</html>