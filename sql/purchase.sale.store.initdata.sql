#create database heh;
#use heh;

charset gbk;
################################

CREATE TABLE `purchase_product` (
  `p_id` varchar(255) DEFAULT NULL,
  `p_name` varchar(30) DEFAULT NULL,
  `p_deleted` bit(1) DEFAULT NULL,
  `p_createTime` datetime DEFAULT NULL,
  `p_modifyTime` datetime DEFAULT NULL,
  `p_creator` varchar(255) DEFAULT NULL,
  `p_modifier` varchar(255) DEFAULT NULL,
  `p_remark` varchar(255) DEFAULT NULL,
  `p_no` varchar(10) DEFAULT NULL,
  `p_memo` varchar(100) DEFAULT NULL,
  `p_size` varchar(10) DEFAULT NULL,
  `p_unit` varchar(5) DEFAULT NULL,
  `p_price` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

################################
INSERT INTO `t_catalogue` VALUES 
('1','栏目列表',NULL,NULL,'cms_info',NULL,NULL,NULL,NULL,NULL,NULL,NULL)
,('13','采购管理','1','0','#',NULL,'2009-07-26 11:09:07',NULL,NULL,NULL,'',NULL)
,('131','采购订货','13','0','purchase_order',NULL,'2009-07-26 11:10:25',NULL,NULL,NULL,'',NULL)
,('132','采购入库','13','0','purchase_store',NULL,'2009-07-26 11:11:36',NULL,NULL,NULL,'',NULL)
,('133','采购退货','13','0','purchase_back',NULL,'2009-07-26 11:12:24',NULL,NULL,NULL,'',NULL)
,('134','付款录单','13','0','purchase_pay',NULL,'2009-07-26 11:13:35',NULL,NULL,NULL,'',NULL)
,('135','付款核销','13','0','purchase_payverify',NULL,'2009-07-26 11:14:31',NULL,NULL,NULL,'',NULL)

,('15','销售管理','1','0','#',NULL,'2009-07-26 11:09:07',NULL,NULL,NULL,'',NULL)
,('151','销售签单','15','0','sale_order',NULL,'2009-07-26 11:10:25',NULL,NULL,NULL,'',NULL)
,('152','销售出库','15','0','sale_out',NULL,'2009-07-26 11:11:36',NULL,NULL,NULL,'',NULL)
,('153','销售退货','15','0','sale_ret',NULL,'2009-07-26 11:12:24',NULL,NULL,NULL,'',NULL)
,('154','收款录单','15','0','sale_rcv',NULL,'2009-07-26 11:13:35',NULL,NULL,NULL,'',NULL)
,('155','收款核销','15','0','sale_rcvverify',NULL,'2009-07-26 11:14:31',NULL,NULL,NULL,'',NULL)

,('16','库存管理','1','0','#',NULL,'2009-07-26 11:09:07',NULL,NULL,NULL,'',NULL)
,('161','其他入库','16','0','store_in',NULL,'2009-07-26 11:10:25',NULL,NULL,NULL,'',NULL)
,('162','其他出库','16','0','store_out',NULL,'2009-07-26 11:11:36',NULL,NULL,NULL,'',NULL)
,('163','调拨','16','0','store_alloc',NULL,'2009-07-26 11:12:24',NULL,NULL,NULL,'',NULL)
,('164','货品盘点','16','0','store_balance',NULL,'2009-07-26 11:13:35',NULL,NULL,NULL,'',NULL);


INSERT INTO `t_catalogright` VALUES 
('11','1','1','1',NULL,NULL,NULL,NULL,NULL,NULL)
,('113','1','1','13',NULL,NULL,NULL,NULL,NULL,NULL)
,('1131','1','1','131',NULL,NULL,NULL,NULL,NULL,NULL)
,('1132','1','1','132',NULL,NULL,NULL,NULL,NULL,NULL)
,('1133','1','1','133',NULL,NULL,NULL,NULL,NULL,NULL)
,('1134','1','1','134',NULL,NULL,NULL,NULL,NULL,NULL)
,('1135','1','1','135',NULL,NULL,NULL,NULL,NULL,NULL)

,('115','1','1','15',NULL,NULL,NULL,NULL,NULL,NULL)
,('1151','1','1','151',NULL,NULL,NULL,NULL,NULL,NULL)
,('1152','1','1','152',NULL,NULL,NULL,NULL,NULL,NULL)
,('1153','1','1','153',NULL,NULL,NULL,NULL,NULL,NULL)
,('1154','1','1','154',NULL,NULL,NULL,NULL,NULL,NULL)
,('1155','1','1','155',NULL,NULL,NULL,NULL,NULL,NULL)

,('116','1','1','16',NULL,NULL,NULL,NULL,NULL,NULL)
,('1161','1','1','161',NULL,NULL,NULL,NULL,NULL,NULL)
,('1162','1','1','162',NULL,NULL,NULL,NULL,NULL,NULL)
,('1163','1','1','163',NULL,NULL,NULL,NULL,NULL,NULL)
,('1164','1','1','164',NULL,NULL,NULL,NULL,NULL,NULL);

#####采购订货
INSERT INTO `t_attribute` VALUES 
('1311','单据编号','131','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1312','供应商','131','supplier','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1313','业务员','131','operateman','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1314','签单日期','131','signdate','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1315','到期日','131','maturedate','12','10','2009-07-26 21:21:39',NULL,NULL,NULL,'',NULL,NULL)
,('1316','单价是否含税','131','tax','12','5','2009-07-26 21:24:45',NULL,NULL,NULL,'',NULL,NULL)
,('1317','备注','131','memo','12','100','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('1318','货品编号','131','productno','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('1319','货品名称','131','productname','12','50','2009-07-26 21:27:08',NULL,NULL,NULL,'',NULL,NULL)
,('131a','规格','131','size','12','10','2009-07-26 21:27:52',NULL,NULL,NULL,'',NULL,NULL)
,('131b','计量单位','131','unit','12','5','2009-07-26 21:28:20',NULL,NULL,NULL,'',NULL,NULL)
,('131c','数量','131','num','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('131d','单价','131','price','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL);

#####采购入库
INSERT INTO `t_attribute` VALUES 
('1321','单据编号','132','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1322','供应商','132','supplier','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1323','仓库','132','store','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1324','业务员','132','operateman','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1325','联系人','132','contactsman','12','10','2009-07-26 21:21:39',NULL,NULL,NULL,'',NULL,NULL)
,('1326','联系人电话','132','contactscall','12','5','2009-07-26 21:24:45',NULL,NULL,NULL,'',NULL,NULL)
,('1327','是否有发票','132','invoice','12','100','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('1328','采购日期','132','purchasedate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('1329','采购订单','132','purchaseno','12','50','2009-07-26 21:27:08',NULL,NULL,NULL,'',NULL,NULL)
,('132a','到期日','132','maturedate','12','10','2009-07-26 21:27:52',NULL,NULL,NULL,'',NULL,NULL)
,('132b','送货地址','132','sendaddress','12','5','2009-07-26 21:28:20',NULL,NULL,NULL,'',NULL,NULL)
,('132c','备注','132','memo','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('132d','货品编号','132','productno','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('132e','货品名称','132','productname','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('132f','规格','132','size','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('132g','计量单位','132','unit','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('132h','数量','132','num','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('132i','单价','132','price','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL);

#####采购退货
INSERT INTO `t_attribute` VALUES 
('1331','单据编号','133','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1332','供应商','133','supplier','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1333','仓库','133','store','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1334','业务员','133','operateman','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1337','是否有发票','133','invoice','12','100','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('1338','退货日期','133','backdate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('1339','采购订单','133','purchaseno','12','50','2009-07-26 21:27:08',NULL,NULL,NULL,'',NULL,NULL)
,('133c','备注','133','memo','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('133d','货品编号','133','productno','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('133e','货品名称','133','productname','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('133f','规格','133','size','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('133g','计量单位','133','unit','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('133h','数量','133','num','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('133i','单价','133','price','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL);

#####付款录单
INSERT INTO `t_attribute` VALUES 
('1341','单据编号','134','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1342','客户或供应商单位','134','payto','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1343','付款原因','134','reason','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('134c','客户或供应商开户行','134','paytobank','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1344','客户或供应商银行户名','134','paytobankname','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1345','客户或供应商帐号','134','paytobankno','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1346','付款日期','134','paydate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('1347','银行户名','134','paybankname','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1348','银行帐号','134','paybankno','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1349','付款金额','134','paysum','12','20','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('134a','业务员','134','operateman','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('134b','备注','134','memo','12','50','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL);

#####付款核销
INSERT INTO `t_attribute` VALUES 
('1351','付款单编号','135','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1356','核销日期','135','dealdate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('1357','单据类型','135','purchasetype','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1358','单据编号','135','purchaseno','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1359','贷款金额','135','sum','12','20','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('135a','核销金额','135','paysum','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL);

####################
#####销售签单
INSERT INTO `t_attribute` VALUES 
('1511','单据编号','151','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1512','客户','151','customer','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1513','业务员','151','operateman','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1514','单价是否含税','151','tax','12','5','2009-07-26 21:24:45',NULL,NULL,NULL,'',NULL,NULL)
,('1515','签单日期','151','signdate','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1516','交货日期','151','senddate','12','10','2009-07-26 21:21:39',NULL,NULL,NULL,'',NULL,NULL)
,('1517','到期日','151','maturedate','12','10','2009-07-26 21:21:39',NULL,NULL,NULL,'',NULL,NULL)
,('1518','备注','151','memo','12','100','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('1519','货品编号','151','productno','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('151a','货品名称','151','productname','12','50','2009-07-26 21:27:08',NULL,NULL,NULL,'',NULL,NULL)
,('151b','规格','151','size','12','10','2009-07-26 21:27:52',NULL,NULL,NULL,'',NULL,NULL)
,('151c','计量单位','151','unit','12','5','2009-07-26 21:28:20',NULL,NULL,NULL,'',NULL,NULL)
,('151d','数量','151','num','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('151e','折前价','151','price','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL)
,('151f','折扣率','151','off','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL);

#####销售出库
INSERT INTO `t_attribute` VALUES 
('1521','单据编号','152','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1522','客户','152','customer','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1523','仓库','152','store','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1524','业务员','152','operateman','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1525','联系人','152','contactsman','12','10','2009-07-26 21:21:39',NULL,NULL,NULL,'',NULL,NULL)
,('1526','联系人电话','152','contactscall','12','5','2009-07-26 21:24:45',NULL,NULL,NULL,'',NULL,NULL)
,('1527','是否有发票','152','invoice','12','100','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('1528','销售日期','152','signdate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('1529','销售签单','152','purchaseno','12','50','2009-07-26 21:27:08',NULL,NULL,NULL,'',NULL,NULL)
,('152a','到期日','152','maturedate','12','10','2009-07-26 21:27:52',NULL,NULL,NULL,'',NULL,NULL)
,('152b','收货地址','152','sendaddress','12','5','2009-07-26 21:28:20',NULL,NULL,NULL,'',NULL,NULL)
,('152c','备注','152','memo','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('152d','货品编号','152','productno','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('152e','货品名称','152','productname','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('152f','规格','152','size','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('152g','计量单位','152','unit','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('152h','出货数量','152','num','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('152i','单价','152','price','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL);

#####销售退库
INSERT INTO `t_attribute` VALUES 
('1531','单据编号','153','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1532','客户','153','customer','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1534','业务员','153','operateman','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1533','仓库','153','store','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1537','是否有发票','153','invoice','12','100','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('1538','退货日期','153','backdate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('1539','来源销售出库单','153','purchaseno','12','50','2009-07-26 21:27:08',NULL,NULL,NULL,'',NULL,NULL)
,('153c','备注','153','memo','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('153d','货品编号','153','productno','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('153e','货品名称','153','productname','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('153f','规格','153','size','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('153g','计量单位','153','unit','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('153h','退货数量','153','num','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('153i','单价','153','price','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL);

#####收款录单
INSERT INTO `t_attribute` VALUES 
('1541','单据编号','154','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1542','收款客户或供应商单位','154','payto','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1543','收款原因','154','reason','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('154c','客户或供应商开户行','154','paybank','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1544','客户或供应商银行户名','154','paybankname','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1545','客户或供应商帐号','154','paybankno','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1546','收款日期','154','rcvdate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('1547','银行户名','154','rcvbankname','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1548','银行帐号','154','rcvbankno','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1549','收款金额','154','rcvsum','12','20','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('154a','业务员','154','operateman','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('154b','备注','154','memo','12','50','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL);

#####收款核销
INSERT INTO `t_attribute` VALUES 
('1551','收款单编号','155','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1556','核销日期','155','dealdate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('1557','单据类型','155','saletype','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1558','单据编号','155','saleno','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1559','贷款金额','155','sum','12','20','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('155a','核销金额','155','rcvsum','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL);

####################
#####其他入库
INSERT INTO `t_attribute` VALUES 
('1611','单据编号','161','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1612','仓库','161','store','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1613','业务员','161','operateman','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1616','入库日期','161','indate','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1618','备注','161','memo','12','100','2009-07-26 21:25:36',NULL,NULL,NULL,'',NULL,NULL)
,('1619','货品编号','161','productno','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('161a','货品名称','161','productname','12','50','2009-07-26 21:27:08',NULL,NULL,NULL,'',NULL,NULL)
,('161b','规格','161','size','12','10','2009-07-26 21:27:52',NULL,NULL,NULL,'',NULL,NULL)
,('161c','计量单位','161','unit','12','5','2009-07-26 21:28:20',NULL,NULL,NULL,'',NULL,NULL)
,('161d','数量','161','num','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('161e','单价','161','price','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL);

#####其他出库
INSERT INTO `t_attribute` VALUES 
('1621','单据编号','162','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1623','仓库','162','store','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('162j','收货单位','162','customer','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1624','业务员','162','operateman','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1628','出库日期','162','outdate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('162c','备注','162','memo','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('162d','货品编号','162','productno','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('162e','货品名称','162','productname','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('162f','规格','162','size','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('162g','计量单位','162','unit','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('162h','数量','162','num','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('162i','成本价','162','price','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL);

#####调拨
INSERT INTO `t_attribute` VALUES 
('1631','单据编号','163','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1632','移出仓库','163','outstore','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1633','移入仓库','163','instore','12','10','2009-07-26 21:18:31',NULL,NULL,NULL,'',NULL,NULL)
,('1634','业务员','163','operateman','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1638','调拨日期','163','allocdate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('163c','备注','163','memo','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('163d','货品编号','163','productno','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('163e','货品名称','163','productname','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('163f','规格','163','size','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('163g','计量单位','163','unit','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('163h','数量','163','num','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('163i','成本价','163','price','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL);

#####货品盘点
INSERT INTO `t_attribute` VALUES 
('1641','单据编号','164','no','12','10','2009-07-26 20:56:27',NULL,NULL,NULL,'',NULL,NULL)
,('1642','仓库','164','store','12','10','2009-07-26 21:17:18',NULL,NULL,NULL,'',NULL,NULL)
,('1644','业务员','164','operateman','12','10','2009-07-26 21:19:14',NULL,NULL,NULL,'',NULL,NULL)
,('1646','盘点日期','164','balancedate','12','10','2009-07-26 21:26:40','2009-07-26 21:27:24',NULL,NULL,'',NULL,NULL)
,('164c','备注','164','memo','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('164d','货品编号','164','productno','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('164e','货品名称','164','productname','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('164f','规格','164','size','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('164g','基准单位','164','unit','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('164h','帐面数量','164','num','12','10','2009-07-26 21:28:47',NULL,NULL,NULL,'',NULL,NULL)
,('164i','实际数量','164','realnum','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL)
,('164j','成本价','164','price','12','10','2009-07-26 21:29:12',NULL,NULL,NULL,'',NULL,NULL);

