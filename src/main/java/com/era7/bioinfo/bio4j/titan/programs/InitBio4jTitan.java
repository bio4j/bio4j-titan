/*
 * Copyright (C) 2010-2013  "Bio4j"
 *
 * This file is part of Bio4j
 *
 * Bio4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.ohnosequences.bio4j.titan.programs;

import com.ohnosequences.bio4j.blueprints.model.nodes.*;
import com.ohnosequences.bio4j.blueprints.model.nodes.citation.*;
import com.ohnosequences.bio4j.blueprints.model.nodes.ncbi.NCBITaxonNode;
import com.ohnosequences.bio4j.blueprints.model.nodes.reactome.ReactomeTermNode;
import com.ohnosequences.bio4j.blueprints.model.nodes.refseq.GenomeElementNode;
import com.ohnosequences.bio4j.blueprints.model.relationships.aproducts.AlternativeProductInitiationRel;
import com.ohnosequences.bio4j.blueprints.model.relationships.aproducts.AlternativeProductPromoterRel;
import com.ohnosequences.bio4j.blueprints.model.relationships.aproducts.AlternativeProductRibosomalFrameshiftingRel;
import com.ohnosequences.bio4j.blueprints.model.relationships.aproducts.AlternativeProductSplicingRel;
import com.ohnosequences.bio4j.blueprints.model.relationships.protein.ProteinErroneousGeneModelPredictionRel;
import com.ohnosequences.bio4j.blueprints.model.relationships.protein.ProteinErroneousInitiationRel;
import com.ohnosequences.bio4j.blueprints.model.relationships.protein.ProteinErroneousTerminationRel;
import com.ohnosequences.bio4j.blueprints.model.relationships.protein.ProteinErroneousTranslationRel;
import com.ohnosequences.bio4j.blueprints.model.relationships.protein.ProteinFrameshiftRel;
import com.ohnosequences.bio4j.blueprints.model.relationships.protein.ProteinMiscellaneousDiscrepancyRel;
import com.ohnosequences.bio4j.model.enums.UniprotDBXref;
import com.ohnosequences.bio4j.titan.model.util.Bio4jManager;
import com.era7.bioinfo.bioinfoneo4j.BasicEntity;
import com.era7.bioinfo.bioinfoutil.Executable;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

import java.util.ArrayList;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

/**
 *
 * @author Pablo Pareja Tobes <ppareja@era7.com>
 */
public class InitBio4jTitan implements Executable {

    @Override
    public void execute(ArrayList<String> array) {
        String[] args = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            args[i] = array.get(i);
        }
        main(args);
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("This program expects the following parameters:\n"
                    + "1. Bio4j DB folder \n");
        } else {


            String folder = args[0];

            //----config---
            Configuration conf = new BaseConfiguration();
            conf.setProperty("storage.directory", folder);
            conf.setProperty("storage.backend", "local");

            System.out.println("Creating DB...");

            Bio4jManager manager = new Bio4jManager(conf);
            TitanGraph graph = manager.getGraph();


            System.out.println("Creating indices...");
            createIndices(graph);
            
            System.out.println("Creating non functiontal keys...");
            createNonFunctionalKeys(graph);    
            
            System.out.println("Creating utility nodes...");
            createAlternativeProductNodes(manager);
            createSequenceCautionNodes(manager);
            
            System.out.println("Shutting down manager...");
            graph.shutdown();            
            
            System.out.println("Done! :)");

        }
    }
    
    private static void createAlternativeProductNodes(Bio4jManager manager){
        
        //--Alternative product INITIATION----
        AlternativeProductNode initiationNode = new AlternativeProductNode(manager.createNode(AlternativeProductNode.NODE_TYPE));
        initiationNode.setName(AlternativeProductInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Alternative product PROMOTER----
        AlternativeProductNode promoterNode = new AlternativeProductNode(manager.createNode(AlternativeProductNode.NODE_TYPE));
        promoterNode.setName(AlternativeProductPromoterRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Alternative product RIBOSOMAL FRAMESHIFTING----
        AlternativeProductNode ribosomalFrameshiftingNode = new AlternativeProductNode(manager.createNode(AlternativeProductNode.NODE_TYPE));
        ribosomalFrameshiftingNode.setName(AlternativeProductRibosomalFrameshiftingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Alternative product SPLICING----
        AlternativeProductNode splicingNode = new AlternativeProductNode(manager.createNode(AlternativeProductNode.NODE_TYPE));
        splicingNode.setName(AlternativeProductSplicingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
    }
    
    private static void createSequenceCautionNodes(Bio4jManager manager){
        
        //--Sequence caution ERRONEOUS GENE MODEL PREDICTION----
        SequenceCautionNode proteinErrGeneModelPredNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinErrGeneModelPredNode.setName(ProteinErroneousGeneModelPredictionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Sequence caution ERRONEOUS INITIATION----
        SequenceCautionNode proteinErrInitiationNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinErrInitiationNode.setName(ProteinErroneousInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Sequence caution ERRONEOUS TRANSLATION----
        SequenceCautionNode proteinErrTranslationNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinErrTranslationNode.setName(ProteinErroneousTranslationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Sequence caution ERRONEOUS TERMINATION----
        SequenceCautionNode proteinErrTerminationNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinErrTerminationNode.setName(ProteinErroneousTerminationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Sequence caution FRAMESHIFT----
        SequenceCautionNode proteinFrameshiftNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinFrameshiftNode.setName(ProteinFrameshiftRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
        //--Sequence caution MISCELLANEOUS DISCREPANCY----
        SequenceCautionNode proteinMiscDiscrepancyNode = new SequenceCautionNode(manager.createNode(SequenceCautionNode.NODE_TYPE));
        proteinMiscDiscrepancyNode.setName(ProteinMiscellaneousDiscrepancyRel.UNIPROT_ATTRIBUTE_TYPE_VALUE);
        
    }
    
    private static void createNonFunctionalKeys(TitanGraph graph){
        
        //---GO TERM---       
        graph.makeKey(GoTermNode.ALTERNATIVE_IDS_PROPERTY).dataType(String.class).unique().indexed(Vertex.class).list().make();
        //---PROTEIN---

        graph.makeKey(ProteinNode.ALTERNATIVE_ACCESSIONS_PROPERTY).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.UNIGENE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.EMBL.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.REFSEQ.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(ProteinNode.GENE_NAMES_PROPERTY).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ENSEMBL_PLANTS.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ALLERGOME.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ARACHNO_SERVER.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.BGEE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.BINDING_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.BIOCYC.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.BRENDA.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.CAZY.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.CGD.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.CHEMBL.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.CLEANEX.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.COMPLUYEAST_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.CONO_SERVER.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.CTD.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.CYGD.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.DBSNP.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.DDBJ.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.DICTY_BASE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.DIP.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.DISPROT.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.DMDM.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.DNASU.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.DOSAC_COBS_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.DRUG_BANK.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ECHO_BASE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ECO_GENE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.EGGNOG.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ENSEMBL_BACTERIA.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ENSEMBL_FUNGI.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ENSEMBL_METAZOA.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ENSEMBL_PROTISTS.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.EUHCV_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.EUPATH_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.EVOLUTIONARY_TRACE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.FLYBASE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENATLAS.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENBANK.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENE3D.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENECARDS.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENEFARM.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENEID.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENETREE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENEVESTIGATOR.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENOLIST.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENOME_REVIEWS.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GENOME_RNAI.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GERMONLINE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GLYCO_SUITE_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GPCR_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.GRAMENE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.HINV_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.HAMAP.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.HGNC.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.HOGENOM.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.HOVERGEN.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.HPA.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.HSSP.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.HUGE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.IMGT.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.INPARANOID.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.INTACT.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.IPI.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.KO.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.KEGG.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.LEGIOLIST.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.LEPROMA.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.MAIZEGD_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.MEROPS.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.MGI.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.MICADO.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.MIM.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.MINT.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.MODBASE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.MYCOCLAP.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.NEXTBIO.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.NEXTPROT.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.OGP.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.OMA.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ORPHANET.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ORTHO_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PANTHER.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PATHWAY_INTERACTION_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PATRIC.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PAXDB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PDB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PDBJ.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PDBSUM.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PEPTIDE_ATLAS.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PEROXIBASE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PHARM_GKB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PHOSPHOSITE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PHOS_SITE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PHYLOME_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PIR.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PIRSF.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PMAP_CUT_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.POMBASE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PPTASE_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PRIDE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PRINTS.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PRODOM.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PROMEX.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PROSITE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PROT_CLUST_DB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PROTEIN_MODEL_PORTAL.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PROTONET.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.PSEUDOCAP.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.RCSBPDB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.REBASE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.REPRODUCTION_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.RGD.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ROUGE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.SBKB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.SGD.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.SMART.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.SMR.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.SOURCE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.STRING.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.SUPFAM.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.SWISS_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.TAIR.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.TCDB.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.TIGRFAMS.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.TUBERCULIST.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.UCD_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.UCSC.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.UNIPATHWAY.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.VECTORBASE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.WORLD_2D_PAGE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.WORMBASE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.XENBASE.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(UniprotDBXref.ZFIN.getProteinReferencePropertyName()).dataType(String.class).unique().indexed(Vertex.class).make();
        
        
        //---NCBI TAXON---                
        graph.makeKey(NCBITaxonNode.GI_IDS_PROPERTY).dataType(String.class).unique().indexed(Vertex.class).make();
        graph.makeKey(NCBITaxonNode.OLD_TAX_IDS_PROPERTY).dataType(String.class).unique().indexed(Vertex.class).make();
        
    }

    private static void createIndices(TitanGraph graph) {

        //------------------------------------------------------------------------
        //--------------------------creating indices------------------------------

        //---NODE TYPE---
        graph.createKeyIndex(BasicEntity.NODE_TYPE_PROPERTY, Vertex.class);

        //---ARTICLE----
        graph.createKeyIndex(ArticleNode.TITLE_PROPERTY, Vertex.class);
        graph.createKeyIndex(ArticleNode.DOI_ID_PROPERTY, Vertex.class);
        graph.createKeyIndex(ArticleNode.MEDLINE_ID_PROPERTY, Vertex.class);
        graph.createKeyIndex(ArticleNode.PUBMED_ID_PROPERTY, Vertex.class);

        //---BOOK----
        graph.createKeyIndex(BookNode.NAME_PROPERTY, Vertex.class);

        //---DB----
        graph.createKeyIndex(DBNode.NAME_PROPERTY, Vertex.class);

        //---JOURNAL---
        graph.createKeyIndex(JournalNode.NAME_PROPERTY, Vertex.class);

        //---ONLINE ARTICLE---
        graph.createKeyIndex(OnlineArticleNode.TITLE_PROPERTY, Vertex.class);

        //---ONLINE JOURNAL---
        graph.createKeyIndex(OnlineJournalNode.NAME_PROPERTY, Vertex.class);

        //---PATENT---
        graph.createKeyIndex(PatentNode.NUMBER_PROPERTY, Vertex.class);

        //---PUBLISHER---
        graph.createKeyIndex(PublisherNode.NAME_PROPERTY, Vertex.class);

        //---SUBMISSION---
        graph.createKeyIndex(SubmissionNode.TITLE_PROPERTY, Vertex.class);

        //---THESIS----
        graph.createKeyIndex(ThesisNode.TITLE_PROPERTY, Vertex.class);

        //---NCBI TAXON--
        graph.createKeyIndex(NCBITaxonNode.TAX_ID_PROPERTY, Vertex.class);
        //gi index is temporarily missing

        //---REACTOME TERM---
        graph.createKeyIndex(ReactomeTermNode.ID_PROPERTY, Vertex.class);

        //---GENOME ELEMENT---
        graph.createKeyIndex(GenomeElementNode.VERSION_PROPERTY, Vertex.class);

        //---ALTERNATIVE PRODUCT---
        graph.createKeyIndex(AlternativeProductNode.NAME_PROPERTY, Vertex.class);

        //---CITY---
        graph.createKeyIndex(CityNode.NAME_PROPERTY, Vertex.class);

        //---COMMENT TYPE---
        graph.createKeyIndex(CommentTypeNode.NAME_PROPERTY, Vertex.class);

        //---CONSORTIUM---
        graph.createKeyIndex(ConsortiumNode.NAME_PROPERTY, Vertex.class);

        //---COUNTRY----
        graph.createKeyIndex(CountryNode.NAME_PROPERTY, Vertex.class);

        //---DATASET---
        graph.createKeyIndex(DatasetNode.NAME_PROPERTY, Vertex.class);

        //---ENZYME---            
        graph.createKeyIndex(EnzymeNode.ID_PROPERTY, Vertex.class);

        //---FEATURE TYPE--
        graph.createKeyIndex(FeatureTypeNode.NAME_PROPERTY, Vertex.class);

        //---GO TERM----
        graph.createKeyIndex(GoTermNode.ID_PROPERTY, Vertex.class);

        //---INSTITUTE---
        graph.createKeyIndex(InstituteNode.NAME_PROPERTY, Vertex.class);

        //---INTERPRO---
        graph.createKeyIndex(InterproNode.ID_PROPERTY, Vertex.class);

        //---ISOFORM---
        graph.createKeyIndex(IsoformNode.ID_PROPERTY, Vertex.class);

        //---KEYWORD---
        graph.createKeyIndex(KeywordNode.ID_PROPERTY, Vertex.class);
        graph.createKeyIndex(KeywordNode.NAME_PROPERTY, Vertex.class);

        //---ORGANISM---
        graph.createKeyIndex(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, Vertex.class);
        graph.createKeyIndex(OrganismNode.SCIENTIFIC_NAME_PROPERTY, Vertex.class);

        //---PERSON---
        graph.createKeyIndex(PersonNode.NAME_PROPERTY, Vertex.class); //This index was fulltext with Neo4j

        //---PFAM---
        graph.createKeyIndex(PfamNode.ID_PROPERTY, Vertex.class);

        //---PROTEIN---
        //Ensmbl plants index is missing here
        //Gene names fulltext index is missing here
        graph.createKeyIndex(ProteinNode.ACCESSION_PROPERTY, Vertex.class);
        graph.createKeyIndex(ProteinNode.FULL_NAME_PROPERTY, Vertex.class); //This index was fulltext with Neo4j

        //---SEQUENCE CAUTION---
        graph.createKeyIndex(SequenceCautionNode.NAME_PROPERTY, Vertex.class);

        //---SUBCELLULAR LOCATION---
        graph.createKeyIndex(SubcellularLocationNode.NAME_PROPERTY, Vertex.class);

        //---TAXON---
        graph.createKeyIndex(TaxonNode.NAME_PROPERTY, Vertex.class);       
        

    }
}
