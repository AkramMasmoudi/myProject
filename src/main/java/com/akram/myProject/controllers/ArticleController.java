package com.akram.myProject.controllers;

import com.akram.myProject.entities.*;
import com.akram.myProject.services.*;
import com.akram.myProject.objects.ArticleVO;
import com.akram.myProject.objects.CategoryVO;
import com.akram.myProject.objects.ResponseObject;
import com.akram.myProject.objects.UnitVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.akram.myProject.globalVariables.Translation.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    private final CoefficientService coefficientService;
    private final PriceService priceService;
    private final QuantityService quantityService;
    private final TranslationService translationService;
    @GetMapping("/all")
    public ResponseEntity<List<ArticleVO>> findAllArticles(){

        try{
            List<ArticleVO> articles = articleService.findAllArticles(EAGER);

            return new ResponseEntity<>(articles, OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/article/{articleId}")
    public ResponseEntity<ArticleVO> findArticleById(@PathVariable Long articleId){

        try{
            ArticleVO article = articleService.findArticleById(articleId,EAGER);
            return new ResponseEntity<>(article, OK);
        }catch (Exception e){
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryVO>> findAllCategories(){

        try{
            List<CategoryVO> categories = articleService.findAllCategories(LAZY);
            return new ResponseEntity<>(categories, OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/units")
    public ResponseEntity<List<UnitVO>> findAllUnits(){

        try{
            List<UnitVO> units = articleService.findAllUnits(LAZY);
            return new ResponseEntity<>(units, OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<>(), INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/article")
    public ResponseEntity<Article> saveArticle(@RequestBody Article article){
        try {
           log.info(article.toString());
            final String name = article.getArticleName();
            final String ref = article.getArticleReference() == null ? "" : article.getArticleReference();
            final Long articleId = article.getArticleId();
            if(name == null || name.isBlank()){
                throw new NullPointerException();
            }
            Optional<Category> category = Optional.of(article.getArticleCategoryId());
            if(category.isPresent() && category.get().getCategoryId() != null)
                category = articleService.findCategoryByCategoryId(category.get().getCategoryId());
            Article articleToSave;
            if(articleId != null){ //modify
                if(category.isPresent()){
                    articleToSave = new Article(articleId ,name,category.get(),ref);
                }else{
                    articleToSave = new Article(articleId ,name,null,ref);
                }
            }else{ //add
                if(category.isPresent()){
                    articleToSave = new Article(name,category.get(),ref);
                }else{
                    articleToSave = new Article(name,null,ref);
                }
            }
            Article articleSaved = articleService.saveArticle(articleToSave);
            articleSaved.setArticleCategoryId(new Category());
            return new ResponseEntity<>(articleSaved, OK);
        }catch (Exception e){
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/coefficient")
    public ResponseEntity<List<Coefficient>> saveCoefficients(@RequestBody Coefficient[] coefficients){
        try{
            if(coefficients.length < 1 || coefficients[0].getCoefficientArticleId() == null)
                return new ResponseEntity<>(INTERNAL_SERVER_ERROR);

            Optional<Article> article = articleService.findArticleById(coefficients[0].getCoefficientArticleId().getArticleId());
            if(!article.isPresent())
                return new ResponseEntity<>(INTERNAL_SERVER_ERROR);

            List<Coefficient> coefficientsToSave = Arrays.stream(coefficients)
                    .map((Coefficient c)-> {
                        c.setCoefficientArticleId(article.get());
                        return c;
                    })
                    .collect(Collectors.toList());
            List<Coefficient> coefficientsSaved = coefficientService.saveAllCoefficient(coefficientsToSave);
            coefficientsSaved.stream().peek((Coefficient c) -> c.setCoefficientArticleId(new Article())).collect(Collectors.toList());
            return new ResponseEntity<>(coefficientsSaved, OK);
        }catch (Exception e){
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/price")
    public ResponseEntity<List<Price>> savePrices(@RequestBody Price[] prices){
        try{
            if(prices.length < 1 || prices[0].getPriceArticleId() == null)
                return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);

            Optional<Article> article = articleService.findArticleById(prices[0].getPriceArticleId().getArticleId());
            if(!article.isPresent())
                return new ResponseEntity<>(null, INTERNAL_SERVER_ERROR);

            List<Price> pricesToSave = Arrays.stream(prices)
                    .map((Price p)-> {
                        p.setPriceArticleId(article.get());
                        return p;
                    })
                    .collect(Collectors.toList());

            List<Price> pricesSaved = priceService.saveAllPrices(pricesToSave);
            pricesSaved.stream().peek((Price p) -> p.setPriceArticleId(new Article())).collect(Collectors.toList());
            return new ResponseEntity<>(pricesSaved, OK);
        }catch (Exception e){
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/quantity")
    public ResponseEntity<List<Quantities>> saveQuantities(@RequestBody Quantities[] quantities){
        try{
            if(quantities.length < 1 || quantities[0].getQuantityArticleId() == null)
                return new ResponseEntity<>(INTERNAL_SERVER_ERROR);

            Optional<Article> article = articleService.findArticleById(quantities[0].getQuantityArticleId().getArticleId());
            if(!article.isPresent())
                return new ResponseEntity<>(INTERNAL_SERVER_ERROR);

            List<Quantities> quantitiesToSave = Arrays.stream(quantities)
                    .map((Quantities q)-> {
                        q.setQuantityArticleId(article.get());
                        return q;
                    })
                    .collect(Collectors.toList());

            List<Quantities> quantitiesSaved = quantityService.saveAllQuantities(quantitiesToSave);
            quantitiesSaved.stream().peek((Quantities q) -> q.setQuantityArticleId(new Article())).collect(Collectors.toList());
            return new ResponseEntity<>(quantitiesSaved, OK);
        }catch (Exception e){
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping ("/safeRemove")
    public ResponseEntity<List<ArticleVO>> removeArticle(@RequestParam(required = true) Long articleId){
        try {
            articleService.safeRemoveArticle(articleId);
            List<ArticleVO> articles = articleService.findAllArticles(EAGER);
            return new ResponseEntity<>(articles, OK);
        }catch (Exception e){
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/unit/add")
    public ResponseEntity<ResponseObject<UnitVO>> addUnit(@RequestBody Unit unit){
        try{
            int res = -1;
            if(unit != null && unit.getName() != null && !unit.getName().isBlank()
                            && unit.getShortName() != null && !unit.getShortName().isBlank()){
                res = articleService.addUnit(unit);
            }
            ResponseObject<UnitVO> resData = new ResponseObject<>();
            String msg = "";
            switch (res){
                case 0:
                    List<UnitVO> units = articleService.findAllUnits(LAZY);
                    resData.setListData(units);
                    break;
                case 1:
                    msg = translationService.getTranslation(NAME_ALREADY_EXIST);
                    resData.setErrorMessage(msg);
                    break;
                case 2:
                    msg = translationService.getTranslation(SHORTNAME_ALREADY_EXIST);
                    resData.setErrorMessage(msg);
                    break;
                default:
                    msg = translationService.getTranslation(NAME_OR_SHORTNAME_EMPTY);
                    resData.setErrorMessage(msg);
            }
            return new ResponseEntity<>(resData, OK);
        }catch (Exception e){
            ResponseObject<UnitVO> resData = new ResponseObject<>();
            resData.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(resData, INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/category/add")
    public ResponseEntity<ResponseObject<CategoryVO>> addCategory(@RequestBody Category category){
        try{
            int res = -1;
            if(category != null && category.getCategoryName() != null && !category.getCategoryName().isBlank()){
                res = articleService.addCategory(category);
            }
            ResponseObject<CategoryVO> resData = new ResponseObject<>();
            String msg = "";
            switch (res){
                case 0:
                    List<CategoryVO> categories = articleService.findAllCategories(LAZY);
                    resData.setListData(categories);
                    break;
                case 1:
                    msg = translationService.getTranslation(NAME_ALREADY_EXIST);
                    resData.setErrorMessage(msg);
                    break;
                default:
                    msg = translationService.getTranslation(NAME_OR_SHORTNAME_EMPTY);
                    resData.setErrorMessage(msg);
            }
            return new ResponseEntity<>(resData, OK);
        }catch (Exception e){
            ResponseObject<CategoryVO> resData = new ResponseObject<>();
            resData.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(resData, INTERNAL_SERVER_ERROR);
        }
    }
}