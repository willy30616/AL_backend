package com.aluminum.second.controller;

import java.net.URI;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aluminum.second.Report;

import com.aluminum.second.domain.CustomerBean;
import com.aluminum.second.domain.MaterialBean;
import com.aluminum.second.domain.MaterialCategoryBean;
import com.aluminum.second.domain.MaterialCostBean;

import com.aluminum.second.domain.MaterialCostJoin;
import com.aluminum.second.domain.ProdSpecBean;
import com.aluminum.second.domain.ProdSpecClc;
import com.aluminum.second.domain.ProdSpecFind;
import com.aluminum.second.domain.ProdSpecJoin;
import com.aluminum.second.domain.ProductBean;
import com.aluminum.second.domain.ReportDetailBean;
import com.aluminum.second.domain.ReportDetailFind;
import com.aluminum.second.domain.ReportDetailJoinAll;
import com.aluminum.second.domain.ReportDetailJoinAll2;
import com.aluminum.second.domain.ReportJoin;

import com.aluminum.second.domain.SpecPriceBean;
import com.aluminum.second.domain.SpecnameBean;
import com.aluminum.second.domain.WebReportBean;
import com.aluminum.second.domain.WebReportCount;
import com.aluminum.second.repository.CustomerRepository;
import com.aluminum.second.repository.MaterialCostRepository;
import com.aluminum.second.repository.MaterialRepository;
import com.aluminum.second.repository.ProdSpecRepository;
import com.aluminum.second.repository.ProductRepository;
import com.aluminum.second.repository.ReportDetailRepository;
import com.aluminum.second.repository.WebReportRepository;

import com.aluminum.second.service.CustomerService;
import com.aluminum.second.service.MaterialCategoryService;
import com.aluminum.second.service.MaterialCostService;
import com.aluminum.second.service.MaterialService;
import com.aluminum.second.service.ProdSpecService;
import com.aluminum.second.service.ProductService;
import com.aluminum.second.service.ReportDetailService;

import com.aluminum.second.service.ReportService;
import com.aluminum.second.service.SpecnameService;
import com.aluminum.second.service.WebReportService;

@RestController
@CrossOrigin
@RequestMapping(path = { "/public/api" })
public class PublicAPI {

	@Autowired
	private CustomerRepository custdao;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private MaterialCostService materialCostService;
	@Autowired
	private SpecnameService specnameService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private MaterialCostRepository materialCostRepository;
	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private WebReportService webReportService;
	@Autowired
	private ReportDetailService reportDetailService;
	@Autowired
	private WebReportRepository webReportRepository;
	@Autowired
	private ReportDetailRepository reportDetailRepository;
	@Autowired
	private ProdSpecRepository prodSpecRepository;
	@Autowired
	private ProdSpecService prodSpecService;
	@Autowired
	private MaterialCategoryService materialCategoryService;

//get
	//????????????????????????@GetMapping
	@GetMapping("/materialcost/join/{specid}")
	public List<MaterialCostJoin> materialCostJoin(@PathVariable("specid") String specid) {

		return materialCostRepository.materialCostJoin(specid);
	}

//	@GetMapping("/materialcost/{id}")
//	public Optional<MaterialCostBean> id(@PathVariable("id")Integer id) {
//		ConfigId configId = new ConfigId();
//		configId.setMaterialid(id);
//		
//		return materialCostRepository.findById(configId);
//		
//	}

	@GetMapping("/materialcost/testupdate")
	public ResponseEntity<MaterialCostBean> test(@RequestBody MaterialCostBean bean) {
		MaterialCostBean result = materialCostService.update(bean);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/materialcost/testupdateall")
	public ResponseEntity<List<MaterialCostBean>> test2(@RequestBody List<MaterialCostBean> bean) {
		List<MaterialCostBean> result = materialCostService.updateAll(bean);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/materialcost/{id1}/{id2}")
	public ResponseEntity<?> id(@PathVariable("id1") String id1, @PathVariable("id2") String id2) {
//		ConfigId configId = new ConfigId();
//		configId.setMaterialid(id);
//		configId.setSpecid(id);
//		return materialCostRepository.findById(configId);
		MaterialCostBean beans = new MaterialCostBean();
		beans.setMaterialid(id1);
		beans.setSpecid(id2);
		boolean result = materialCostService.delete(beans);
		if (result) {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}
	}

	// ????????????
	@GetMapping("/report/{format}/{reportid}/{company}")
	public ResponseEntity<byte[]> generateReport(@PathVariable String format, @PathVariable String reportid, @PathVariable String company)
			throws Exception {
		return reportService.exportReport(format, reportid,company);

	}

	// ??????????????????
	@GetMapping("/customer")
	public ResponseEntity<List<CustomerBean>> selectCustAll() {
		List<CustomerBean> result = custdao.findAll();

		return ResponseEntity.ok(result);
	}

	// ??????????????????
	@GetMapping("/material")
	public ResponseEntity<List<MaterialBean>> selectMeterialAll() {
		List<MaterialBean> result = materialService.selectAll();
		return ResponseEntity.ok(result);
	}

	// ??????????????????
	@GetMapping("/findmaterial/{materialid}")
	public ResponseEntity<Boolean> findMeterial(@PathVariable("materialid") String materialid) {
		boolean result = materialRepository.existsById(materialid);
		return ResponseEntity.ok(result);
	}

	// ??????????????????
	@GetMapping("/product")
	public ResponseEntity<List<ProductBean>> selectProduct() {
		List<ProductBean> result = productService.selectAll();
		return ResponseEntity.ok(result);
	}

	// ??????????????????
	@GetMapping("/webreport")
	public ResponseEntity<List<WebReportBean>> selectReport() {
		List<WebReportBean> result = webReportService.selectAll();
		return ResponseEntity.ok(result);
	}

	// ????????????????????????
	@GetMapping("/webreport/{id}")
	public ResponseEntity<Optional<WebReportBean>> selectOneReport(@PathVariable("id") String reportid) {
		Optional<WebReportBean> result = webReportRepository.findById(reportid);
		return ResponseEntity.ok(result);
	}

	// ????????????????????????
	@GetMapping("/reportdetail")
	public ResponseEntity<List<ReportDetailBean>> selectReportDetail() {
		List<ReportDetailBean> result = reportDetailService.selectAll();
		return ResponseEntity.ok(result);
	}

	// ????????????????????????
	@GetMapping("/specname/{specid}")
	public ResponseEntity<Optional<SpecnameBean>> selectOneSpecname(@PathVariable("specid") String specid) {
		SpecnameBean bean = new SpecnameBean();
		bean.setSpecid(specid);
		Optional<SpecnameBean> result = specnameService.selectOne(bean);
		return ResponseEntity.ok(result);
	}

	// ????????????????????????
	@GetMapping("/product/{productid}")
	public ResponseEntity<Optional<ProductBean>> selectOneProduct(@PathVariable("productid") String productid) {

		Optional<ProductBean> result = productRepository.findById(productid);
		return ResponseEntity.ok(result);
	}

	// ????????????????????????
	@GetMapping("/customer/{id}")
	public ResponseEntity<Optional<CustomerBean>> selectOneCust(@PathVariable("id") Integer customerid) {

		Optional<CustomerBean> result = custdao.findById(customerid);
		return ResponseEntity.ok(result);
	}

	// ????????????????????????
	@GetMapping("/materialcost")
	public ResponseEntity<List<MaterialCostBean>> materialCost() {
		List<MaterialCostBean> result = materialCostService.findAll();
		return ResponseEntity.ok(result);
	}

	// ????????????????????????
	@GetMapping("/findspec/{id}")
	public ResponseEntity<?> findSpec(@PathVariable("id") String specid) {
		List<ProdSpecFind> result = prodSpecRepository.findSpecUsed(specid);
		if (result != null) {
			return ResponseEntity.ok(result);
		} else {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		}

	}

	// ????????????????????????
	@GetMapping("/specprice")
	public ResponseEntity<List<SpecPriceBean>> specPrice() {
		List<SpecPriceBean> result = materialCostService.selectSpecPrice();
		return ResponseEntity.ok(result);
	}

	// ????????????????????????
	@GetMapping("/doorleafprice")
	public ResponseEntity<List<SpecPriceBean>> doorleafPrice() {
		List<SpecPriceBean> result = materialCostService.selectDoorLeafPrice();
		return ResponseEntity.ok(result);
	}

	// ????????????????????????
	@GetMapping("/frameprice")
	public ResponseEntity<List<SpecPriceBean>> framePrice() {
		List<SpecPriceBean> result = materialCostService.selectFramePrice();
		return ResponseEntity.ok(result);
	}

	// ????????????????????????
	@GetMapping("/prodspecprice")
	public ResponseEntity<List<ProdSpecClc>> prodspecPrice() {
		List<ProdSpecClc> result = prodSpecRepository.selectPrice();
		return ResponseEntity.ok(result);
	}

	// ????????????join??????
	@GetMapping("/reportjoin")
	public ResponseEntity<List<ReportJoin>> reportjoin() {
		List<ReportJoin> result = reportDetailService.ReportJoin();
		return ResponseEntity.ok(result);
	}

	// ??????????????????join??????
	@GetMapping("/reportdetailjoinall/{reportid}")
	public ResponseEntity<List<ReportDetailJoinAll>> reportdetailjoinall(@PathVariable("reportid") String reportid) {
		List<ReportDetailJoinAll> result = reportDetailService.ReportDetailJoinAll(reportid);
		return ResponseEntity.ok(result);
	}

	// ??????????????????JOIN??????
	@GetMapping("/specprod/{prodid}")
	public ResponseEntity<List<ProdSpecJoin>> method(@PathVariable("prodid") String prodid) {
		List<ProdSpecJoin> result = prodSpecRepository.prodSpecJoin(prodid);
		return ResponseEntity.ok(result);
	}

	// ??????????????????join??????
	@GetMapping("/reportdetailjoinall2/{reportid}")
	public ResponseEntity<List<ReportDetailJoinAll2>> reportdetailjoinall2(@PathVariable("reportid") String reportid) {
		List<ReportDetailJoinAll2> result = reportDetailRepository.ReportDetailJoinAll2(reportid);
		return ResponseEntity.ok(result);
	}

	// where????????????????????????join
	@GetMapping("/reportdetailfind/{prodid}")
	public ResponseEntity<List<ReportDetailFind>> reportDetailFind(@PathVariable("prodid") String prodid) {
		List<ReportDetailFind> result = reportDetailRepository.reportDetailFind(prodid);
		return ResponseEntity.ok(result);
	}

	// ??????????????????
	@GetMapping("/specname")
	public ResponseEntity<List<SpecnameBean>> specName() {
		List<SpecnameBean> result = specnameService.selectAll();
		return ResponseEntity.ok(result);
	}

	// ???????????????????????????
	@GetMapping("/webreportcount")
	public ResponseEntity<List<WebReportCount>> webreportCount() {

		List<WebReportCount> result = webReportRepository.webreportCount();
		return ResponseEntity.ok(result);
	}

	// ??????????????????by WHERE??????
	@GetMapping("/findcustomer/{anythig}")
	public ResponseEntity<List<CustomerBean>> findcustomer(@PathVariable String anythig) {

		if (anythig != " ") {

			List<CustomerBean> result = customerService.findByAnything(anythig);
			return ResponseEntity.ok(result);
		} else {
			List<CustomerBean> result = custdao.findAll();
			return ResponseEntity.ok(result);
		}

	}

	// ??????????????????
	@GetMapping("/materialcategory")
	public ResponseEntity<List<MaterialCategoryBean>> materialCategorySelect() {

		List<MaterialCategoryBean> result = materialCategoryService.selectAll();
		return ResponseEntity.ok(result);
	}
	// ??????????????????
	@GetMapping("/findmaterialcategory/{id}")
	public ResponseEntity<Boolean> materialCategoryFind(@PathVariable("id") Integer id) {

		boolean result = materialCategoryService.find(id);
		return ResponseEntity.ok(result);
	}

	// post
	//????????????????????????@PostMapping
	@PostMapping("/customer")
	public ResponseEntity<?> insert(@RequestBody CustomerBean bean) {

		CustomerBean result = customerService.insert(bean);
		if (result != null) {
			ResponseEntity<CustomerBean> entity = ResponseEntity
					.created(URI.create("/public/api/customer/" + result.getCustomerid())).body(result);
			System.out.println(entity);
			return entity;

		} else {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		}

	}

	@PostMapping("/materialcategory")
	public ResponseEntity<?> materialCategoryInsert(@RequestBody MaterialCategoryBean bean) {

		MaterialCategoryBean result = materialCategoryService.insert(bean);
		if (result != null) {
			ResponseEntity<MaterialCategoryBean> entity = ResponseEntity
					.created(URI.create("/public/api/materialcategory/" + result.getCategory())).body(result);
			System.out.println(entity);
			return entity;

		} else {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		}

	}

	@PostMapping("/specname")
	public ResponseEntity<?> insertSpec(@RequestBody SpecnameBean bean) {
		System.out.println(bean);
		SpecnameBean result = specnameService.insert(bean);
		System.out.println("00" + result);
		if (result != null) {
			ResponseEntity<SpecnameBean> entity = ResponseEntity
					.created(URI.create("/public/api/specname/" + result.getSpecid())).body(result);
			System.out.println(entity);
			return entity;

		} else {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		}

	}

	@PostMapping("/material")
	public ResponseEntity<?> insertMaterial(@RequestBody MaterialBean bean) {
		MaterialBean result = materialService.insert(bean);
		if (result != null) {
			ResponseEntity<MaterialBean> entity = ResponseEntity
					.created(URI.create("/public/api/material/" + result.getMaterialid())).body(result);
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		}

	}

	@PostMapping("/product")
	public ResponseEntity<?> insertProduct(@RequestBody ProductBean bean) {
		System.out.println(bean);
		ProductBean result = productService.insert(bean);
		if (result != null) {
			ResponseEntity<ProductBean> entity = ResponseEntity
					.created(URI.create("/public/api/product" + result.getProdid())).body(result);
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		}
	}

	@PostMapping("/webreport")
	public ResponseEntity<?> insertWebReport(@RequestBody WebReportBean bean) {
		WebReportBean result = webReportService.insert(bean);
		if (result != null) {
			ResponseEntity<WebReportBean> entity = ResponseEntity
					.created(URI.create("/public/api/webreport" + result.getReportid())).body(result);
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		}
	}

	@PostMapping("/materialcost")
	public ResponseEntity<?> materialcostInsert(@RequestBody List<MaterialCostBean> beans) {

		List<MaterialCostBean> result = materialCostService.insertAll(beans);
		if (result != null) {
			ResponseEntity<List<MaterialCostBean>> entity = ResponseEntity
					.created(URI.create("/public/api/materialcost/" + result.get(0).getMaterialid())).body(result);
//			System.out.println(entity);
			return entity;

		} else {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		}

	}

	@PostMapping("/reportdetail")
	public ResponseEntity<?> reportdetailInsert(@RequestBody List<ReportDetailBean> beans) {
		List<ReportDetailBean> result = reportDetailService.insertAll(beans);
		if (result != null) {
			ResponseEntity<List<ReportDetailBean>> entity = ResponseEntity
					.created(URI.create("/public/api/reportdetail" + beans.get(0).getReportid())).body(result);
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		}
	}

//put
	//????????????????????????@PutMapping
	@PutMapping("/customer/{id}")
	public ResponseEntity<?> CustomerUpdate(@PathVariable("id") Integer id, @RequestBody CustomerBean bean) {
//		System.out.println(id + ":" + bean);
		CustomerBean result = customerService.update(bean);
		if (result != null && id == bean.getCustomerid()) {
			ResponseEntity<CustomerBean> entity = ResponseEntity.ok(result);
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}

	}

	@PutMapping("/materialcategory/{id}")
	public ResponseEntity<?> materialCategoryUpdate(@PathVariable("id") Integer id,
			@RequestBody MaterialCategoryBean bean) {
//		System.out.println(id + ":" + bean);
		MaterialCategoryBean result = materialCategoryService.update(bean);
		if (result != null && id == bean.getMaterialcategoryid()) {
			ResponseEntity<MaterialCategoryBean> entity = ResponseEntity.ok(result);
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}

	}

	@PutMapping("/material/{id}")
	public ResponseEntity<?> MaterialUpdate(@PathVariable("id") Integer id, @RequestBody MaterialBean bean) {

		MaterialBean result = materialService.update(bean);
		System.out.println(result);
		if (result != null && id.equals(bean.getMaterialid())) {
			ResponseEntity<MaterialBean> entity = ResponseEntity.ok(result);
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}

	}

	@PutMapping("/specname/{id}")
	public ResponseEntity<?> SpecnameUpdate(@PathVariable("id") String id, @RequestBody SpecnameBean bean) {
		SpecnameBean result = specnameService.update(bean);
		if (result != null && id.equals(bean.getSpecid())) {
			ResponseEntity<SpecnameBean> entity = ResponseEntity.ok(result);
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}
	}

	@PutMapping("/webreport/{id}")
	public ResponseEntity<?> WebReportUpdate(@PathVariable("id") String id, @RequestBody WebReportBean bean) {
		WebReportBean result = webReportService.update(bean);
		if (result != null && id.equals(bean.getReportid())) {
			ResponseEntity<WebReportBean> entity = ResponseEntity.ok(result);
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<?> ProductUpdate(@PathVariable("id") String id, @RequestBody ProductBean bean) {
		System.out.println(bean);
		ProductBean result = productService.update(bean);
		if (result != null && id.equals(bean.getProdid())) {
			ResponseEntity<ProductBean> entity = ResponseEntity.ok(result);
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}
	}

//delete
	//????????????????????????@DeleteMapping
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> CustomerRemove(@PathVariable("id") Integer id) {
		CustomerBean bean = new CustomerBean();
		bean.setCustomerid(id);
		boolean result = customerService.delete(bean);
		if (result) {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}
	}

	@DeleteMapping("/materialcategory/{id}")
	public ResponseEntity<?> materialCategoryRemove(@PathVariable("id") Integer id) {
		MaterialCategoryBean bean = new MaterialCategoryBean();
		bean.setMaterialcategoryid(id);
		boolean result = materialCategoryService.delete(bean);
		if (result) {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}
	}

	@DeleteMapping("/material/{id}")
	public ResponseEntity<?> MaterialRemove(@PathVariable("id") String id) {
		MaterialBean bean = new MaterialBean();
		bean.setMaterialid(id);
		boolean result = materialService.delete(bean);
		if (result) {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}
	}

	@DeleteMapping("/specname/{specid}")
	public ResponseEntity<?> SpecnameRemove(@PathVariable("specid") String specid) {
		SpecnameBean bean = new SpecnameBean();
		bean.setSpecid(specid);
		boolean result = specnameService.delete(bean);
		if (result) {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> ProductDelete(@PathVariable("id") String id) {
		ProductBean bean = new ProductBean();
		bean.setProdid(id);
		boolean result = productService.delete(bean);
		if (result) {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}
	}

	@DeleteMapping("/materialcost/deleteall")
	public ResponseEntity<?> materialcostdeletes(@RequestBody List<MaterialCostBean> beans) {
		if (materialCostService.deleteAll(beans)) {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		} else {
			System.out.println("ok");
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}

	}

	@DeleteMapping("/prodspec/deleteall")
	public ResponseEntity<?> prodSpecdeletes(@RequestBody List<ProdSpecBean> beans) {
		if (prodSpecService.deleteAll(beans)) {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		} else {
			System.out.println("ok");
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}

	}

	@DeleteMapping("/reportdetail/deleteall")
	public ResponseEntity<?> reportdetaildeletes(@RequestBody List<ReportDetailBean> beans) {
		if (reportDetailService.deleteAll(beans)) {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}

	}

	@DeleteMapping("/webreport/{id}")
	public ResponseEntity<?> WebreportDelete(@PathVariable("id") String id) {
		WebReportBean bean = new WebReportBean();
		bean.setReportid(id);
		boolean result = webReportService.delete(bean);
		if (result) {
			ResponseEntity<?> entity = ResponseEntity.noContent().build();
			return entity;
		} else {
			ResponseEntity<?> entity = ResponseEntity.notFound().build();
			return entity;
		}
	}

//	@GetMapping("/export1")
//	public ResponseEntity<byte[]> exportExcel() throws Exception {
//		// ????????????
////		Map<String, Object> params = new HashMap<String, Object>();
//		// ?????????
//		List<MaterialBean> list = materialService.selectAll();
//
//		Map<String, Object> beanParams = new HashMap<String, Object>();
//		beanParams.put("createdAt", new Date());
//		beanParams.put("materials", list);
//		// ????????????
//		return Report.downLoadExcel("static/excel/ccc.xls", beanParams);
//	}

}
