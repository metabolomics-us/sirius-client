package us.metabolomics.sirius.sirius_client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultLists {
    @JsonProperty("formulas")
    private List<String> formulas;
    @JsonProperty("sirius_scores")
    private List<Float> sirius_scores;
    @JsonProperty("adducts")
    private List<String> adducts;
    @JsonProperty("precursor_formulas")
    private List<String> precursor_formulas;

    // Default constructor, initialize empty lists
    public ResultLists() {
        this.formulas = new ArrayList<>();
        this.sirius_scores = new ArrayList<>();
        this.adducts = new ArrayList<>();
        this.precursor_formulas = new ArrayList<>();
    }

    // Custom constructor
    public ResultLists(List<String> formulas, List<Float> scores, List<String> adducts, List<String> precursors) {
        this.formulas = formulas;
        this.sirius_scores = scores;
        this.adducts = adducts;
        this.precursor_formulas = precursors;
    }

    // GETTERS
    public List<String> getFormulas() {
        return formulas;
    }

    public List<Float> getScores() {
        return sirius_scores;
    }

    public List<String> getAdducts() {
        return adducts;
    }

    public List<String> getPrecursors() {
        return precursor_formulas;
    }

    //SETTERS
    public void setFormulas(List<String> new_formulas) {
        this.formulas = new_formulas;
    }

    public void setScores(List<Float> new_scores) {
        this.sirius_scores = new_scores;
    }

    public void setAdducts(List<String> new_adducts) {
        this.adducts = new_adducts;
    }

    public void setPrecursors(List<String> new_precursors) {
        this.precursor_formulas = new_precursors;
    }
}
