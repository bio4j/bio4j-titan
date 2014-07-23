package com.bio4j.titan.programs;

import com.bio4j.model.uniprot.UniprotGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.model.uniprot.TitanUniprotGraphImpl;
import com.bio4j.titan.model.uniprot.nodes.*;
import com.bio4j.titan.model.uniprot.relationships.TitanProteinFeature;
import com.ohnosequences.util.Executable;
import com.ohnosequences.xml.model.bio4j.UniprotDataXML;
import com.ohnosequences.xml.api.model.XMLElement;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.jdom2.Element;

/**
 *
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 */
public class ImportUniprotTitan implements Executable {

	private static final Logger logger = Logger.getLogger("ImportUniprot");
	private static FileHandler fh;

	public static final String ENTRY_TAG_NAME = "entry";
	public static final String ENTRY_ACCESSION_TAG_NAME = "accession";
	public static final String ENTRY_NAME_TAG_NAME = "name";
	public static final String ENTRY_MODIFIED_DATE_ATTRIBUTE = "modified";
	public static final String ENTRY_CREATED_DATE_ATTRIBUTE = "created";
	public static final String ENTRY_VERSION_ATTRIBUTE = "version";
	public static final String ENTRY_DATASET_ATTRIBUTE = "dataset";
	public static final String ENTRY_SEQUENCE_TAG_NAME = "sequence";

	public static final String KEYWORD_TAG_NAME = "keyword";
	public static final String KEYWORD_ID_ATTRIBUTE = "id";

	public static final String REFERENCE_TAG_NAME = "reference";
	public static final String CITATION_TAG_NAME = "citation";

	public static final String ORGANISM_TAG_NAME = "organism";
	public static final String ORGANISM_NAME_TAG_NAME = "name";
	public static final String ORGANISM_NAME_TYPE_ATTRIBUTE = "type";
	public static final String ORGANISM_SCIENTIFIC_NAME_TYPE = "scientific";
	public static final String ORGANISM_COMMON_NAME_TYPE = "common";
	public static final String ORGANISM_SYNONYM_NAME_TYPE = "synonym";

	public static final String DB_REFERENCE_TAG_NAME = "dbReference";
	public static final String DB_REFERENCE_TYPE_ATTRIBUTE = "type";
	public static final String DB_REFERENCE_ID_ATTRIBUTE = "id";
	public static final String DB_REFERENCE_VALUE_ATTRIBUTE = "value";
	public static final String DB_REFERENCE_PROPERTY_TAG_NAME = "property";

	public static final String INTERPRO_DB_REFERENCE_TYPE = "InterPro";
	public static final String INTERPRO_ENTRY_NAME = "entry name";

	public static final String GO_DB_REFERENCE_TYPE = "GO";
	public static final String EVIDENCE_TYPE_ATTRIBUTE = "evidence";

	public static final String SEQUENCE_MASS_ATTRIBUTE = "mass";
	public static final String SEQUENCE_LENGTH_ATTRIBUTE = "length";
	public static final String PROTEIN_TAG_NAME = "protein";
	public static final String PROTEIN_RECOMMENDED_NAME_TAG_NAME = "recommendedName";
	public static final String PROTEIN_FULL_NAME_TAG_NAME = "fullName";
	public static final String PROTEIN_SHORT_NAME_TAG_NAME = "shortName";
	public static final String GENE_TAG_NAME = "gene";
	public static final String GENE_NAME_TAG_NAME = "name";
	public static final String COMMENT_TAG_NAME = "comment";
	public static final String COMMENT_TYPE_ATTRIBUTE = "type";
	public static final String COMMENT_ALTERNATIVE_PRODUCTS_TYPE = "alternative products";
	public static final String COMMENT_SEQUENCE_CAUTION_TYPE = "sequence caution";
	public static final String SUBCELLULAR_LOCATION_TAG_NAME = "subcellularLocation";
	public static final String LOCATION_TAG_NAME = "location";
	public static final String COMMENT_TEXT_TAG_NAME = "text";
	public static final String FEATURE_TAG_NAME = "feature";
	public static final String FEATURE_TYPE_ATTRIBUTE = "type";
	public static final String FEATURE_DESCRIPTION_ATTRIBUTE = "description";
	public static final String STATUS_ATTRIBUTE = "status";
	public static final String FEATURE_REF_ATTRIBUTE = "ref";
	public static final String FEATURE_ID_ATTRIBUTE = "id";
	public static final String EVIDENCE_ATTRIBUTE = "evidence";
	public static final String FEATURE_LOCATION_TAG_NAME = "location";
	public static final String FEATURE_ORIGINAL_TAG_NAME = "original";
	public static final String FEATURE_VARIATION_TAG_NAME = "variation";
	public static final String FEATURE_POSITION_TAG_NAME = "position";
	public static final String FEATURE_LOCATION_BEGIN_TAG_NAME = "begin";
	public static final String FEATURE_LOCATION_END_TAG_NAME = "end";
	public static final String FEATURE_LOCATION_POSITION_ATTRIBUTE = "position";
	public static final String FEATURE_POSITION_POSITION_ATTRIBUTE = "position";
	public static final String FEATURE_ACTIVE_SITE_UNIPROT_ATTRIBUTE_VALUE = "active site";
	public static final String FEATURE_BINDING_SITE_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_CROSS_LINK_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_GLYCOSYLATION_SITE_UNIPROT_ATTRIBUTE_VALUE = "glycosylation site";
	public static final String FEATURE_INITIATOR_METHIONINE_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_LIPID_MOIETY_BINDING_REGION_UNIPROT_ATTRIBUTE_VALUE = "lipid moiety-binding region";
	public static final String FEATURE_METAL_ION_BINDING_SITE_UNIPROT_ATTRIBUTE_VALUE = "metal ion-binding site";
	public static final String FEATURE_MODIFIED_RESIDUE_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_NON_STANDARD_AMINOACID_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_NON_TERMINAL_RESIDUE_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_PEPTIDE_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_UNSURE_RESIDUE_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_MUTAGENESIS_SITE_UNIPROT_ATTRIBUTE_VALUE = "mutagenesis site";
	public static final String FEATURE_SEQUENCE_VARIANT_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_CALCIUM_BINDING_REGION_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_CHAIN_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_COILED_COIL_REGION_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_COMPOSITONALLY_BIASED_REGION_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_DISULFIDE_BOND_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_DNA_BINDING_REGION_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_DOMAIN_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_HELIX_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_INTRAMEMBRANE_REGION_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_NON_CONSECUTIVE_RESIDUES_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_NUCLEOTIDE_PHOSPHATE_BINDING_REGION_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_PROPEPTIDE_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_REGION_OF_INTEREST_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_REPEAT_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_SHORT_SEQUENCE_MOTIF_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_SIGNAL_PEPTIDE_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_SPLICE_VARIANT_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_STRAND_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_TOPOLOGICAL_DOMAIN_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_TRANSIT_PEPTIDE_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_TRANSMEMBRANE_REGION_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_ZINC_FINGER_REGION_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_SITE_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_TURN_UNIPROT_ATTRIBUTE_VALUE = "";
	public static final String FEATURE_SEQUENCE_CONFLICT_UNIPROT_ATTRIBUTE_VALUE = "";

	@Override
	public void execute(ArrayList<String> array) {
		String[] args = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			args[i] = array.get(i);
		}
		main(args);
	}

	public static void main(String[] args) {

		if (args.length != 3) {
			System.out.println("This program expects the following parameters: \n"
					+ "1. Uniprot xml filename \n"
					+ "2. Bio4j DB folder \n"
					+ "3. Config XML file");
		} else {

			long initTime = System.nanoTime();

			File inFile = new File(args[0]);
			File configFile = new File(args[2]);

			String currentAccessionId = "";

			//----------DB configuration------------------
			Configuration conf = new BaseConfiguration();
			conf.setProperty("storage.directory", args[1]);
			conf.setProperty("storage.backend", "local");
			conf.setProperty("autotype", "none");

			//-------creating graph handlers---------------------
			TitanGraph g = TitanFactory.open(conf);
			TitanUniprotGraphImpl graph = new TitanUniprotGraphImpl(g);


			BufferedWriter enzymeIdsNotFoundBuff = null;
			BufferedWriter statsBuff = null;

			int proteinCounter = 0;
			int limitForPrintingOut = 10000;

			try {

				// This block configures the logger with handler and formatter
				fh = new FileHandler("ImportUniprot" + args[0].split("\\.")[0] + ".log", false);

				SimpleFormatter formatter = new SimpleFormatter();
				fh.setFormatter(formatter);
				logger.addHandler(fh);
				logger.setLevel(Level.ALL);

				System.out.println("Reading conf file...");
				BufferedReader reader = new BufferedReader(new FileReader(configFile));
				String line;
				StringBuilder stBuilder = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					stBuilder.append(line);
				}
				reader.close();

				UniprotDataXML uniprotDataXML = new UniprotDataXML(stBuilder.toString());

				//---creating writer for enzymes not found file-----
				enzymeIdsNotFoundBuff = new BufferedWriter(new FileWriter(new File("EnzymeIdsNotFound.log")));

				//---creating writer for stats file-----
				statsBuff = new BufferedWriter(new FileWriter(new File("ImportUniprotStats_" + inFile.getName().split("\\.")[0] + ".txt")));

				reader = new BufferedReader(new FileReader(inFile));
				StringBuilder entryStBuilder = new StringBuilder();

				while ((line = reader.readLine()) != null) {
					if (line.trim().startsWith("<" + ENTRY_TAG_NAME)) {

						while (!line.trim().startsWith("</" + ENTRY_TAG_NAME + ">")) {
							entryStBuilder.append(line);
							line = reader.readLine();
						}
						//linea final del organism
						entryStBuilder.append(line);
						//System.out.println("organismStBuilder.toString() = " + organismStBuilder.toString());
						XMLElement entryXMLElem = new XMLElement(entryStBuilder.toString());
						entryStBuilder.delete(0, entryStBuilder.length());

						String modifiedDateSt = entryXMLElem.asJDomElement().getAttributeValue(ENTRY_MODIFIED_DATE_ATTRIBUTE);
						String createdDateSt = entryXMLElem.asJDomElement().getAttributeValue(ENTRY_CREATED_DATE_ATTRIBUTE);
						Integer version = Integer.parseInt(entryXMLElem.asJDomElement().getAttributeValue(ENTRY_VERSION_ATTRIBUTE));

						String accessionSt = entryXMLElem.asJDomElement().getChildText(ENTRY_ACCESSION_TAG_NAME);
						String nameSt = entryXMLElem.asJDomElement().getChildText(ENTRY_NAME_TAG_NAME);
						String fullNameSt = getProteinFullName(entryXMLElem.asJDomElement().getChild(PROTEIN_TAG_NAME));
						String shortNameSt = getProteinShortName(entryXMLElem.asJDomElement().getChild(PROTEIN_TAG_NAME));

						if (shortNameSt == null) {
							shortNameSt = "";
						}
						if (fullNameSt == null) {
							fullNameSt = "";
						}

						currentAccessionId = accessionSt;

						// TODO review whether these should be stored or not
//						//-----------alternative accessions-------------
//						ArrayList<String> alternativeAccessions = new ArrayList<>();
//						List<Element> altAccessionsList = entryXMLElem.asJDomElement().getChildren(UniprotStuff.ENTRY_ACCESSION_TAG_NAME);
//						for (int i = 1; i < altAccessionsList.size(); i++) {
//							alternativeAccessions.add(altAccessionsList.get(i).getText());
//						}

						Element sequenceElem = entryXMLElem.asJDomElement().getChild(ENTRY_SEQUENCE_TAG_NAME);
						String sequenceSt = sequenceElem.getText();
						int seqLength = Integer.parseInt(sequenceElem.getAttributeValue(SEQUENCE_LENGTH_ATTRIBUTE));
						float seqMass = Float.parseFloat(sequenceElem.getAttributeValue(SEQUENCE_MASS_ATTRIBUTE));


						TitanProtein protein = graph.proteinT.from(graph.rawGraph().addVertex(null));

						protein.set(graph.proteinT.modifiedDate, modifiedDateSt);
						protein.set(graph.proteinT.createdDate, createdDateSt);
						protein.set(graph.proteinT.accession, accessionSt);
						protein.set(graph.proteinT.name, nameSt);
						protein.set(graph.proteinT.fullName, fullNameSt);
						protein.set(graph.proteinT.shortName, shortNameSt);
						protein.set(graph.proteinT.sequence, sequenceSt);
						protein.set(graph.proteinT.length, seqLength);
						protein.set(graph.proteinT.mass, String.valueOf(seqMass));
						protein.set(graph.proteinT.version, version);

						//-----db references-------------
						List<Element> dbReferenceList = entryXMLElem.asJDomElement().getChildren(DB_REFERENCE_TAG_NAME);
						ArrayList<String> enzymeDBReferences = new ArrayList<>();
						ArrayList<String> ensemblPlantsReferences = new ArrayList<>();
						HashMap<String, String> reactomeReferences = new HashMap<>();

						for (Element dbReferenceElem : dbReferenceList) {
							String refId = dbReferenceElem.getAttributeValue("id");
							switch (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE)) {
								case "Ensembl":
									//looking for Ensembl node
									TitanEnsembl ensembl = null;
									Optional<TitanEnsembl> ensemblOptional = graph.ensemblIdIndex.getNode(refId);
									if(!ensemblOptional.isPresent()){
										String moleculeIdSt = "";
										String proteinSequenceIdSt = "";
										String geneIdSt = "";
										List<Element> children = dbReferenceElem.getChildren("property");
										for (Element propertyElem : children) {
											if (propertyElem.getAttributeValue("type").equals("protein sequence ID")) {
												proteinSequenceIdSt = propertyElem.getAttributeValue("value");
											}
											if (propertyElem.getAttributeValue("type").equals("gene ID")) {
												geneIdSt = propertyElem.getAttributeValue("value");
											}
										}
										Element moleculeTag = dbReferenceElem.getChild("molecule");
										if(moleculeTag != null){
											moleculeIdSt = moleculeTag.getAttributeValue("id");
											if(moleculeIdSt == null){
												moleculeTag.getText();
												if(moleculeIdSt == null){
													moleculeIdSt = "";
												}
											}
										}

										ensembl = graph.ensemblT.from(graph.rawGraph().addVertex(null));
										ensembl.set(graph.ensemblT.id, refId);
										ensembl.set(graph.ensemblT.proteinSequenceId, proteinSequenceIdSt);
										ensembl.set(graph.ensemblT.moleculeId, moleculeIdSt);
										ensembl.set(graph.ensemblT.geneId, geneIdSt);
										g.commit();
									}else{
										ensembl = ensemblOptional.get();
									}
									protein.addOut(graph.proteinEnsemblT, ensembl);
									break;
								case "PIR":
									//looking for PIR node
									TitanPIR pIR = null;
									Optional<TitanPIR> optionalPIR = graph.pIRIdIndex.getNode(refId);
									if(!optionalPIR.isPresent()){
										String entryNameSt = "";
										List<Element> children = dbReferenceElem.getChildren("property");
										for (Element propertyElem : children) {
											if (propertyElem.getAttributeValue("type").equals("entry name")) {
												entryNameSt = propertyElem.getAttributeValue("value");
											}
										}
										pIR = graph.pIRT.from(graph.rawGraph().addVertex(null));
										pIR.set(graph.pIRT.entryName, entryNameSt);
										pIR.set(graph.pIRT.id, refId);
										g.commit();
									}else{
										pIR = optionalPIR.get();
									}
									protein.addOut(graph.proteinPIRT, pIR);

									break;
								case "UniGene":
									//looking for UniGene node
									TitanUniGene uniGene = null;
									Optional<TitanUniGene> uniGeneOptional = graph.uniGeneIdIndex.getNode(refId);
									if(!uniGeneOptional.isPresent()){
										uniGene = graph.uniGeneT.from(graph.rawGraph().addVertex(null));
										uniGene.set(graph.uniGeneT.id, refId);
										g.commit();
									}else{
										uniGene = uniGeneOptional.get();
									}
									protein.addOut(graph.proteinUniGeneT, uniGene);
									break;
								case "KEGG":
									//looking for Kegg node
									TitanKegg kegg = null;
									Optional<TitanKegg> optionalKegg = graph.keggIdIndex.getNode(refId);
									if(!optionalKegg.isPresent()){
										kegg = graph.keggT.from(graph.rawGraph().addVertex(null));
										kegg.set(graph.keggT.id, refId);
										g.commit();
									}else{
										kegg = optionalKegg.get();
									}
									protein.addOut(graph.proteinKeggT, kegg);
									break;
								case "EMBL":
									//looking for EMBL node
									TitanEMBL embl = null;
									Optional<TitanEMBL> optionalEMBL = graph.eMBLIdIndex.getNode(refId);
									if(!optionalEMBL.isPresent()){
										String moleculeTypeSt = "";
										String proteinSequenceIdSt = "";
										List<Element> children = dbReferenceElem.getChildren("property");
										for (Element propertyElem : children) {
											if (propertyElem.getAttributeValue("type").equals("protein sequence ID")) {
												proteinSequenceIdSt = propertyElem.getAttributeValue("value");
											}
											if (propertyElem.getAttributeValue("type").equals("molecule type")) {
												moleculeTypeSt = propertyElem.getAttributeValue("value");
											}
										}

										embl = graph.eMBLT.from(graph.rawGraph().addVertex(null));
										embl.set(graph.eMBLT.id, refId);
										embl.set(graph.eMBLT.proteinSequenceId, proteinSequenceIdSt);
										embl.set(graph.eMBLT.moleculeType, moleculeTypeSt);
										g.commit();
									}else{
										embl = optionalEMBL.get();
									}
									protein.addOut(graph.proteinEMBLT, embl);
									break;
								case "EC":
									enzymeDBReferences.add(refId);
									break;
								case "RefSeq":
									//looking for RefSeq node
									TitanRefSeq refSeq = null;
									Optional<TitanRefSeq> optionalRefSeq = graph.refSeqIdIndex.getNode(refId);
									if(!optionalRefSeq.isPresent()){
										String nucleotideSequenceIdSt = "";
										List<Element> children = dbReferenceElem.getChildren("property");
										for (Element propertyElem : children) {
											if (propertyElem.getAttributeValue("type").equals("nucleotide sequence ID")) {
												nucleotideSequenceIdSt = propertyElem.getAttributeValue("value");
											}
										}

										refSeq = graph.refSeqT.from(graph.rawGraph().addVertex(null));
										refSeq.set(graph.refSeqT.id, refId);
										refSeq.set(graph.refSeqT.nucleotideSequenceId, nucleotideSequenceIdSt);
										g.commit();
									}else{
										refSeq = optionalRefSeq.get();
									}
									protein.addOut(graph.proteinRefSeqT, refSeq);
									break;
								case "Reactome":
									Element propertyElem = dbReferenceElem.getChild("property");
									String pathwayName = "";
									if (propertyElem.getAttributeValue("type").equals("pathway name")) {
										pathwayName = propertyElem.getAttributeValue("value");
									}
									reactomeReferences.put(refId, pathwayName);
									break;
								case "EnsemblPlants":
									ensemblPlantsReferences.add(refId);
									break;
							}

						}


//						proteinProperties.put(ProteinNode.ENSEMBL_PLANTS_REFERENCES_PROPERTY, convertToStringArray(ensemblPlantsReferences));


						// TODO we need to decide how to store this
//						//---------------gene-names-------------------
//						Element geneElement = entryXMLElem.asJDomElement().getChild(GENE_TAG_NAME);
//						ArrayList<String> geneNames = new ArrayList<>();
//						if (geneElement != null) {
//							List<Element> genesList = geneElement.getChildren(GENE_NAME_TAG_NAME);
//							for (Element geneNameElem : genesList) {
//								geneNames.add(geneNameElem.getText());
//							}
//						}
//						//-----------------------------------------


						//--------------reactome associations----------------
						if (uniprotDataXML.getReactome()) {
							for (String reactomeId : reactomeReferences.keySet()) {

								TitanReactomeTerm reactomeTerm = null;
								Optional<TitanReactomeTerm> optionalReactomeTerm = graph.reactomeTermIdIndex.getNode(reactomeId);

								if (!optionalReactomeTerm.isPresent()) {
									reactomeTerm = graph.reactomeTermT.from(graph.rawGraph().addVertex(null));
									reactomeTerm.set(graph.reactomeTermT.id, reactomeId);
									reactomeTerm.set(graph.reactomeTermT.pathwayName, reactomeReferences.get(reactomeId));
									g.commit();
								}else{
									reactomeTerm = optionalReactomeTerm.get();
								}
								protein.addOut(graph.proteinReactomeTermT, reactomeTerm);
							}
						}
						//-------------------------------------------------------


//						//---------------enzyme db associations----------------------
//						if (uniprotDataXML.getEnzymeDb()) {
//							for (String enzymeDBRef : enzymeDBReferences) {
//								//TitanEnzyme enzyme = graph..getNode(reactomeId);
//								long enzymeNodeId;
//								IndexHits<Long> enzymeIdIndexHits = enzymeIdIndex.get(EnzymeNode.ENZYME_ID_INDEX, enzymeDBRef);
//								if (enzymeIdIndexHits.hasNext()) {
//									enzymeNodeId = enzymeIdIndexHits.next();
//									inserter.createRelationship(currentProteinId, enzymeNodeId, proteinEnzymaticActivityRel, null);
//								} else {
//									enzymeIdsNotFoundBuff.write("Enzyme term: " + enzymeDBRef + " not found.\t" + currentAccessionId);
//								}
//							}
//						}
//						//------------------------------------------------------------


						//-----comments import---
						if (uniprotDataXML.getComments()) {
							//importProteinComments(entryXMLElem, graph, protein, sequenceSt, uniprotDataXML);
						}

						//-----features import----
						if (uniprotDataXML.getFeatures()) {
							//importProteinFeatures(entryXMLElem, inserter, indexProvider, currentProteinId);
						}

						//--------------------------------datasets--------------------------------------------------
						String proteinDataSetSt = entryXMLElem.asJDomElement().getAttributeValue(ENTRY_DATASET_ATTRIBUTE);

						TitanDataset dataset = null;
						Optional<TitanDataset> optionalDataset = graph.datasetNameIndex.getNode(proteinDataSetSt);

						if (!optionalDataset.isPresent()) {
							dataset = graph.datasetT.from(graph.rawGraph().addVertex(null));
							dataset.set(graph.datasetT.name, proteinDataSetSt);
							g.commit();
						}else{
							dataset = optionalDataset.get();
						}
						protein.addOut(graph.proteinDatasetT, dataset);
						//---------------------------------------------------------------------------------------------


//						if (uniprotDataXML.getCitations()) {
//							importProteinCitations(entryXMLElem,
//									inserter,
//									indexProvider,
//									currentProteinId,
//									uniprotDataXML);
//						}


						//-------------------------------keywords------------------------------------------------------
						if (uniprotDataXML.getKeywords()) {
							List<Element> keywordsList = entryXMLElem.asJDomElement().getChildren(KEYWORD_TAG_NAME);
							for (Element keywordElem : keywordsList) {
								String keywordId = keywordElem.getAttributeValue(KEYWORD_ID_ATTRIBUTE);
								String keywordName = keywordElem.getText();

								TitanKeyword keyword = null;
								Optional<TitanKeyword> optionalKeyword = graph.keywordIdIndex.getNode(keywordId);

								if (!optionalKeyword.isPresent()) {
									keyword = graph.keywordT.from(graph.rawGraph().addVertex(null));
									keyword.set(graph.keywordT.id, keywordId);
									keyword.set(graph.keywordT.name, keywordName);
									g.commit();
								}else{
									keyword = optionalKeyword.get();
								}
								protein.addOut(graph.proteinKeywordT, keyword);
							}
						}
						//---------------------------------------------------------------------------------------


						for (Element dbReferenceElem : dbReferenceList) {

							//-------------------------------INTERPRO------------------------------------------------------
							if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals(INTERPRO_DB_REFERENCE_TYPE)) {

								if (uniprotDataXML.getInterpro()) {
									String interproId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);

									TitanInterpro interpro = null;
									Optional<TitanInterpro> optionalInterpro = graph.interproIdIndex.getNode(interproId);

									if (!optionalInterpro.isPresent()) {

										String interproEntryNameSt = "";
										List<Element> properties = dbReferenceElem.getChildren(DB_REFERENCE_PROPERTY_TAG_NAME);
										for (Element prop : properties) {
											if (prop.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals(INTERPRO_ENTRY_NAME)) {
												interproEntryNameSt = prop.getAttributeValue(DB_REFERENCE_VALUE_ATTRIBUTE);
												break;
											}
										}

										interpro = graph.interproT.from(graph.rawGraph().addVertex(null));
										interpro.set(graph.interproT.id, interproId);
										interpro.set(graph.interproT.name, interproEntryNameSt);
										g.commit();
									}else{
										interpro = optionalInterpro.get();
									}

									protein.addOut(graph.proteinInterproT, interpro);
								}

							} //-------------------------------PFAM------------------------------------------------------
							else if (dbReferenceElem.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals("Pfam")) {

								if (uniprotDataXML.getPfam()) {

									String pfamId = dbReferenceElem.getAttributeValue(DB_REFERENCE_ID_ATTRIBUTE);
									TitanPfam pfam = null;
									Optional<TitanPfam> optionalPfam = graph.pfamIdIndex.getNode(pfamId);

									if (!optionalPfam.isPresent()) {
										String pfamEntryNameSt = "";
										List<Element> properties = dbReferenceElem.getChildren(DB_REFERENCE_PROPERTY_TAG_NAME);
										for (Element prop : properties) {
											if (prop.getAttributeValue(DB_REFERENCE_TYPE_ATTRIBUTE).equals("entry name")) {
												pfamEntryNameSt = prop.getAttributeValue(DB_REFERENCE_VALUE_ATTRIBUTE);
												break;
											}
										}

										pfam = graph.pfamT.from(graph.rawGraph().addVertex(null));
										pfam.set(graph.pfamT.id, pfamId);
										pfam.set(graph.pfamT.name, pfamEntryNameSt);
										g.commit();
									}else{
										pfam = optionalPfam.get();
									}

									protein.addOut(graph.proteinPfamT, pfam);
								}


							}

						}
						//---------------------------------------------------------------------------------------

						//---------------------------------------------------------------------------------------
						//--------------------------------organism-----------------------------------------------

						String scName, commName, synName;
						scName = "";
						commName = "";
						synName = "";

						Element organismElem = entryXMLElem.asJDomElement().getChild(ORGANISM_TAG_NAME);

						List<Element> organismNames = organismElem.getChildren(ORGANISM_NAME_TAG_NAME);
						for (Element element : organismNames) {
							String type = element.getAttributeValue(ORGANISM_NAME_TYPE_ATTRIBUTE);
							switch (type) {
								case ORGANISM_SCIENTIFIC_NAME_TYPE:
									scName = element.getText();
									break;
								case ORGANISM_COMMON_NAME_TYPE:
									commName = element.getText();
									break;
								case ORGANISM_SYNONYM_NAME_TYPE:
									synName = element.getText();
									break;
							}
						}

						TitanOrganism organism = null;
						Optional<TitanOrganism> organismOptional = graph.organismScientificNameIndex.getNode(scName);

						if (!organismOptional.isPresent()) {

							organism = graph.organismT.from(graph.rawGraph().addVertex(null));
							organism.set(graph.organismT.scientificName, scName);
							organism.set(graph.organismT.commonName, commName);
							organism.set(graph.organismT.synonymName, synName);
							g.commit();

							/* TODO see what to do with the NCBI taxonomy ID, just link to the NCBI tax node or also store
							    the id as an attribute
							*/
//							List<Element> organismDbRefElems = organismElem.getChildren(DB_REFERENCE_TAG_NAME);
//							boolean ncbiIdFound = false;
//							if (organismDbRefElems != null) {
//								for (Element dbRefElem : organismDbRefElems) {
//									String t = dbRefElem.getAttributeValue("type");
//									if (t.equals("NCBI Taxonomy")) {
//										organismProperties.put(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, dbRefElem.getAttributeValue("id"));
//										ncbiIdFound = true;
//										break;
//									}
//								}
//							}
//							if (!ncbiIdFound) {
//								organismProperties.put(OrganismNode.NCBI_TAXONOMY_ID_PROPERTY, "");
//							}

							Element lineage = entryXMLElem.asJDomElement().getChild("organism").getChild("lineage");
							List<Element> taxons = lineage.getChildren("taxon");

							Element firstTaxonElem = taxons.get(0);

							TitanTaxon firstTaxon = null;
							Optional<TitanTaxon> firstTaxonOptional = graph.taxonNameIndex.getNode(firstTaxonElem.getText());

							if (!firstTaxonOptional.isPresent()) {

								String firstTaxonName = firstTaxonElem.getText();
								firstTaxon = graph.taxonT.from(graph.rawGraph().addVertex(null));
								firstTaxon.set(graph.taxonT.name, firstTaxonName);
								g.commit();

							}else{
								firstTaxon = firstTaxonOptional.get();
							}

							TitanTaxon lastTaxon = firstTaxon;

							for (int i = 1; i < taxons.size(); i++) {
								String taxonName = taxons.get(i).getText();
								TitanTaxon currentTaxon = null;
								Optional<TitanTaxon> currentTaxonOptional = graph.taxonNameIndex.getNode(taxonName);

								if (!currentTaxonOptional.isPresent()) {

									currentTaxon = graph.taxonT.from(graph.rawGraph().addVertex(null));
									currentTaxon.set(graph.taxonT.name, taxonName);
									g.commit();
									lastTaxon.addOut(graph.taxonParentT, currentTaxon);
								}else{
									currentTaxon = currentTaxonOptional.get();
								}
								lastTaxon = currentTaxon;
							}


							organism.addOut(graph.organismTaxonT, lastTaxon);

						}else{
							organism = organismOptional.get();
						}


						//---------------------------------------------------------------------------------------
						//---------------------------------------------------------------------------------------

						protein.addOut(graph.proteinOrganismT, organism);

						proteinCounter++;
						if ((proteinCounter % limitForPrintingOut) == 0) {
							String countProteinsSt = proteinCounter + " proteins inserted!!";
							logger.log(Level.INFO, countProteinsSt);
						}

					}
				}

			} catch (Exception e) {
				logger.log(Level.SEVERE, ("Exception retrieving protein " + currentAccessionId));
				logger.log(Level.SEVERE, e.getMessage());
				StackTraceElement[] trace = e.getStackTrace();
				for (StackTraceElement stackTraceElement : trace) {
					logger.log(Level.SEVERE, stackTraceElement.toString());
				}
			} finally {

				try {
					//------closing writers-------
					enzymeIdsNotFoundBuff.close();

					// shutdown, makes sure all changes are written to disk
					graph.rawGraph().shutdown();

					// closing logger file handler
					fh.close();

					//-----------------writing stats file---------------------
					long elapsedTime = System.nanoTime() - initTime;
					long elapsedSeconds = Math.round((elapsedTime / 1000000000.0));
					long hours = elapsedSeconds / 3600;
					long minutes = (elapsedSeconds % 3600) / 60;
					long seconds = (elapsedSeconds % 3600) % 60;

					statsBuff.write("Statistics for program ImportUniprot:\nInput file: " + inFile.getName()
							+ "\nThere were " + proteinCounter + " proteins inserted.\n"
							+ "The elapsed time was: " + hours + "h " + minutes + "m " + seconds + "s\n");

					//---closing stats writer---
					statsBuff.close();


				} catch (IOException ex) {
					Logger.getLogger(ImportUniprotTitan.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
		}

	}

	private static void importProteinFeatures(XMLElement entryXMLElem,
	                                          TitanUniprotGraph graph,
	                                          TitanProtein protein) {

		//--------------------------------features----------------------------------------------------
		List<Element> featuresList = entryXMLElem.asJDomElement().getChildren(FEATURE_TAG_NAME);

		for (Element featureElem : featuresList) {

			String featureTypeSt = featureElem.getAttributeValue(FEATURE_TYPE_ATTRIBUTE);

			TitanFeatureType feature = null;
			Optional<TitanFeatureType> optionalFeature = graph.featureTypeNameIndex.getNode(featureTypeSt);

			if (!optionalFeature.isPresent()) {

				feature = graph.featureTypeT.from(graph.rawGraph().addVertex(null));
				feature.set(graph.featureTypeT.name, featureTypeSt);
				graph.rawGraph().commit();

			}else{
				feature = optionalFeature.get();
			}

			String featureDescSt = featureElem.getAttributeValue(FEATURE_DESCRIPTION_ATTRIBUTE);
			if (featureDescSt == null) {
				featureDescSt = "";
			}
			String featureIdSt = featureElem.getAttributeValue(FEATURE_ID_ATTRIBUTE);
			if (featureIdSt == null) {
				featureIdSt = "";
			}
			String featureStatusSt = featureElem.getAttributeValue(STATUS_ATTRIBUTE);
			if (featureStatusSt == null) {
				featureStatusSt = "";
			}
			String featureEvidenceSt = featureElem.getAttributeValue(EVIDENCE_ATTRIBUTE);
			if (featureEvidenceSt == null) {
				featureEvidenceSt = "";
			}

			Element locationElem = featureElem.getChild(FEATURE_LOCATION_TAG_NAME);
			Element positionElem = locationElem.getChild(FEATURE_POSITION_TAG_NAME);
			Integer beginFeature = null;
			Integer endFeature = null;
			if (positionElem != null) {
				beginFeature = Integer.parseInt(positionElem.getAttributeValue(FEATURE_POSITION_POSITION_ATTRIBUTE));
				endFeature = beginFeature;
			} else {
				beginFeature = Integer.parseInt(locationElem.getChild(FEATURE_LOCATION_BEGIN_TAG_NAME).getAttributeValue(FEATURE_LOCATION_POSITION_ATTRIBUTE));
				endFeature = Integer.parseInt(locationElem.getChild(FEATURE_LOCATION_END_TAG_NAME).getAttributeValue(FEATURE_LOCATION_POSITION_ATTRIBUTE));
			}

			if (beginFeature == null) {
				beginFeature = -1;
			}
			if (endFeature == null) {
				endFeature = -1;
			}

			String originalSt = featureElem.getChildText(FEATURE_ORIGINAL_TAG_NAME);
			String variationSt = featureElem.getChildText(FEATURE_VARIATION_TAG_NAME);
			if (originalSt == null) {
				originalSt = "";
			}
			if (variationSt == null) {
				variationSt = "";
			}
			String featureRefSt = featureElem.getAttributeValue(FEATURE_REF_ATTRIBUTE);
			if (featureRefSt == null) {
				featureRefSt = "";
			}

			TitanProteinFeature proteinFeature = protein.addOut(graph.proteinFeatureT, feature);
			addPropertiesToProteinFeatureRelationship(graph, proteinFeature, featureIdSt, featureDescSt, featureEvidenceSt,
					featureStatusSt, beginFeature, endFeature, originalSt, variationSt, featureRefSt);


//			switch (featureTypeSt) {
//				case FEATURE_ACTIVE_SITE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_BINDING_SITE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_CROSS_LINK_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_GLYCOSYLATION_SITE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_INITIATOR_METHIONINE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_LIPID_MOIETY_BINDING_REGION_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_METAL_ION_BINDING_SITE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_MODIFIED_RESIDUE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_NON_STANDARD_AMINOACID_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_NON_TERMINAL_RESIDUE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_PEPTIDE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_UNSURE_RESIDUE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_MUTAGENESIS_SITE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_SEQUENCE_VARIANT_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_CALCIUM_BINDING_REGION_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_CHAIN_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_COILED_COIL_REGION_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_COMPOSITONALLY_BIASED_REGION_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_DISULFIDE_BOND_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_DNA_BINDING_REGION_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_DOMAIN_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_HELIX_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_INTRAMEMBRANE_REGION_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_NON_CONSECUTIVE_RESIDUES_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_NUCLEOTIDE_PHOSPHATE_BINDING_REGION_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_PROPEPTIDE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_REGION_OF_INTEREST_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_REPEAT_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_SHORT_SEQUENCE_MOTIF_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_SIGNAL_PEPTIDE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_SPLICE_VARIANT_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_STRAND_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_TOPOLOGICAL_DOMAIN_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_TRANSIT_PEPTIDE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_TRANSMEMBRANE_REGION_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_ZINC_FINGER_REGION_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_SITE_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_TURN_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//				case FEATURE_SEQUENCE_CONFLICT_UNIPROT_ATTRIBUTE_VALUE:
//					break;
//			}

		}

	}

//	private static void importProteinComments(XMLElement entryXMLElem,
//	                                          TitanUniprotGraph graph,
//	                                          TitanProtein currentProtein,
//	                                          String proteinSequence,
//	                                          UniprotDataXML uniprotDataXML) {
//
//
//		List<Element> comments = entryXMLElem.asJDomElement().getChildren(COMMENT_TAG_NAME);
//
//		for (Element commentElem : comments) {
//
//			String commentTypeSt = commentElem.getAttributeValue(COMMENT_TYPE_ATTRIBUTE);
//
//			Element textElem = commentElem.getChild("text");
//			String commentTextSt = "";
//			String commentStatusSt = "";
//			String commentEvidenceSt = "";
//			if (textElem != null) {
//				commentTextSt = textElem.getText();
//				commentStatusSt = textElem.getAttributeValue("status");
//				if (commentStatusSt == null) {
//					commentStatusSt = "";
//				}
//				commentEvidenceSt = textElem.getAttributeValue("evidence");
//				if (commentEvidenceSt == null) {
//					commentEvidenceSt = "";
//				}
//			}
//
//
//
//			commentProperties.put(BasicCommentRel.TEXT_PROPERTY, commentTextSt);
//			commentProperties.put(BasicCommentRel.STATUS_PROPERTY, commentStatusSt);
//			commentProperties.put(BasicCommentRel.EVIDENCE_PROPERTY, commentEvidenceSt);
//
//			//-----------------COMMENT TYPE NODE RETRIEVING/CREATION----------------------
//			Optional<TitanCommentType> commentOptional =  graph.commentTypeNameIndex.getNode(commentTypeSt);
//			TitanCommentType comment = null;
//
//			if(!commentOptional.isPresent()){
//				comment = graph.commentTypeT.from(graph.rawGraph().addVertex(null));
//				comment.set(graph.commentTypeT.name, commentTypeSt);
//				graph.rawGraph().commit();
//			}else{
//				comment = commentOptional.get();
//			}
//			//-----toxic dose----------------
//			switch (commentTypeSt) {
//				case ToxicDoseCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, toxicDoseCommentRel, commentProperties);
//					break;
//				case CautionCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, cautionCommentRel, commentProperties);
//					break;
//				case CofactorCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, cofactorCommentRel, commentProperties);
//					break;
//				case DiseaseCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, diseaseCommentRel, commentProperties);
//					break;
//				case OnlineInformationCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					onlineInformationCommentProperties.put(OnlineInformationCommentRel.STATUS_PROPERTY, commentStatusSt);
//					onlineInformationCommentProperties.put(OnlineInformationCommentRel.EVIDENCE_PROPERTY, commentEvidenceSt);
//					onlineInformationCommentProperties.put(OnlineInformationCommentRel.TEXT_PROPERTY, commentTextSt);
//					String nameSt = commentElem.getAttributeValue("name");
//					if (nameSt == null) {
//						nameSt = "";
//					}
//					String linkSt = "";
//					Element linkElem = commentElem.getChild("link");
//					if (linkElem != null) {
//						String uriSt = linkElem.getAttributeValue("uri");
//						if (uriSt != null) {
//							linkSt = uriSt;
//						}
//					}
//					onlineInformationCommentProperties.put(OnlineInformationCommentRel.NAME_PROPERTY, nameSt);
//					onlineInformationCommentProperties.put(OnlineInformationCommentRel.LINK_PROPERTY, linkSt);
//					inserter.createRelationship(currentProteinId, commentTypeId, onlineInformationCommentRel, onlineInformationCommentProperties);
//					break;
//				case TissueSpecificityCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, tissueSpecificityCommentRel, commentProperties);
//					break;
//				case FunctionCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, functionCommentRel, commentProperties);
//					break;
//				case BiotechnologyCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, biotechnologyCommentRel, commentProperties);
//					break;
//				case SubunitCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, subunitCommentRel, commentProperties);
//					break;
//				case PolymorphismCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, polymorphismCommentRel, commentProperties);
//					break;
//				case DomainCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, domainCommentRel, commentProperties);
//					break;
//				case PostTranslationalModificationCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, postTranslationalModificationCommentRel, commentProperties);
//					break;
//				case CatalyticActivityCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, catalyticActivityCommentRel, commentProperties);
//					break;
//				case DisruptionPhenotypeCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, disruptionPhenotypeCommentRel, commentProperties);
//					break;
//				case BioPhysicoChemicalPropertiesCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.STATUS_PROPERTY, commentStatusSt);
//					biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.EVIDENCE_PROPERTY, commentEvidenceSt);
//					biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.TEXT_PROPERTY, commentTextSt);
//					String phDependenceSt = commentElem.getChildText("phDependence");
//					String temperatureDependenceSt = commentElem.getChildText("temperatureDependence");
//					if (phDependenceSt == null) {
//						phDependenceSt = "";
//					}
//					if (temperatureDependenceSt == null) {
//						temperatureDependenceSt = "";
//					}
//					String absorptionMaxSt = "";
//					String absorptionTextSt = "";
//					Element absorptionElem = commentElem.getChild("absorption");
//					if (absorptionElem != null) {
//						absorptionMaxSt = absorptionElem.getChildText("max");
//						absorptionTextSt = absorptionElem.getChildText("text");
//						if (absorptionMaxSt == null) {
//							absorptionMaxSt = "";
//						}
//						if (absorptionTextSt == null) {
//							absorptionTextSt = "";
//						}
//					}
//					String kineticsSt = "";
//					Element kineticsElem = commentElem.getChild("kinetics");
//					if (kineticsElem != null) {
//						kineticsSt = new XMLElement(kineticsElem).toString();
//					}
//					String redoxPotentialSt = "";
//					String redoxPotentialEvidenceSt = "";
//					Element redoxPotentialElem = commentElem.getChild("redoxPotential");
//					if (redoxPotentialElem != null) {
//						redoxPotentialSt = redoxPotentialElem.getText();
//						redoxPotentialEvidenceSt = redoxPotentialElem.getAttributeValue("evidence");
//						if (redoxPotentialSt == null) {
//							redoxPotentialSt = "";
//						}
//						if (redoxPotentialEvidenceSt == null) {
//							redoxPotentialEvidenceSt = "";
//						}
//					}
//					biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.TEMPERATURE_DEPENDENCE_PROPERTY, temperatureDependenceSt);
//					biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.PH_DEPENDENCE_PROPERTY, phDependenceSt);
//					biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.KINETICS_XML_PROPERTY, kineticsSt);
//					biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.ABSORPTION_MAX_PROPERTY, absorptionMaxSt);
//					biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.ABSORPTION_TEXT_PROPERTY, absorptionTextSt);
//					biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.REDOX_POTENTIAL_EVIDENCE_PROPERTY, redoxPotentialEvidenceSt);
//					biophysicochemicalCommentProperties.put(BioPhysicoChemicalPropertiesCommentRel.REDOX_POTENTIAL_PROPERTY, redoxPotentialSt);
//					inserter.createRelationship(currentProteinId, commentTypeId, bioPhysicoChemicalPropertiesCommentRel, biophysicochemicalCommentProperties);
//					break;
//				case AllergenCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, allergenCommentRel, commentProperties);
//					break;
//				case PathwayCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, pathwayCommentRel, commentProperties);
//					break;
//				case InductionCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, inductionCommentRel, commentProperties);
//					break;
//				case ProteinSubcellularLocationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					if (uniprotDataXML.getSubcellularLocations()) {
//						List<Element> subcLocations = commentElem.getChildren(UniprotStuff.SUBCELLULAR_LOCATION_TAG_NAME);
//
//						for (Element subcLocation : subcLocations) {
//
//							List<Element> locations = subcLocation.getChildren(UniprotStuff.LOCATION_TAG_NAME);
//							Element firstLocation = locations.get(0);
//							//long firstLocationId = indexService.getSingleNode(SubcellularLocationNode.SUBCELLULAR_LOCATION_NAME_INDEX, firstLocation.getTextTrim());
//							long firstLocationId = -1;
//							IndexHits<Long> firstLocationIndexHits = subcellularLocationNameIndex.get(SubcellularLocationNode.SUBCELLULAR_LOCATION_NAME_INDEX, firstLocation.getTextTrim());
//							if (firstLocationIndexHits.hasNext()) {
//								firstLocationId = firstLocationIndexHits.getSingle();
//							}
//							firstLocationIndexHits.close();
//							long lastLocationId = firstLocationId;
//
//							if (firstLocationId < 0) {
//								subcellularLocationProperties.put(SubcellularLocationNode.NAME_PROPERTY, firstLocation.getTextTrim());
//								lastLocationId = createSubcellularLocationNode(subcellularLocationProperties, inserter, subcellularLocationNameIndex, nodeTypeIndex);
//								//---flushing subcellular location name index---
//								subcellularLocationNameIndex.flush();
//							}
//
//							for (int i = 1; i < locations.size(); i++) {
//
//								long tempLocationId;
//								IndexHits<Long> tempLocationIndexHits = subcellularLocationNameIndex.get(SubcellularLocationNode.SUBCELLULAR_LOCATION_NAME_INDEX, locations.get(i).getTextTrim());
//								if (tempLocationIndexHits.hasNext()) {
//									tempLocationId = tempLocationIndexHits.getSingle();
//									tempLocationIndexHits.close();
//								} else {
//									subcellularLocationProperties.put(SubcellularLocationNode.NAME_PROPERTY, locations.get(i).getTextTrim());
//									tempLocationId = createSubcellularLocationNode(subcellularLocationProperties, inserter, subcellularLocationNameIndex, nodeTypeIndex);
//									subcellularLocationNameIndex.flush();
//								}
//
//								inserter.createRelationship(tempLocationId, lastLocationId, subcellularLocationParentRel, null);
//								lastLocationId = tempLocationId;
//							}
//							Element lastLocation = locations.get(locations.size() - 1);
//							String evidenceSt = lastLocation.getAttributeValue(UniprotStuff.EVIDENCE_ATTRIBUTE);
//							String statusSt = lastLocation.getAttributeValue(UniprotStuff.STATUS_ATTRIBUTE);
//							String topologyStatusSt = "";
//							String topologySt = "";
//							Element topologyElem = subcLocation.getChild("topology");
//							if (topologyElem != null) {
//								topologySt = topologyElem.getText();
//								topologyStatusSt = topologyElem.getAttributeValue("status");
//							}
//							if (topologyStatusSt == null) {
//								topologyStatusSt = "";
//							}
//							if (topologySt == null) {
//								topologySt = "";
//							}
//							if (evidenceSt == null) {
//								evidenceSt = "";
//							}
//							if (statusSt == null) {
//								statusSt = "";
//							}
//							proteinSubcellularLocationProperties.put(ProteinSubcellularLocationRel.EVIDENCE_PROPERTY, evidenceSt);
//							proteinSubcellularLocationProperties.put(ProteinSubcellularLocationRel.STATUS_PROPERTY, statusSt);
//							proteinSubcellularLocationProperties.put(ProteinSubcellularLocationRel.TOPOLOGY_PROPERTY, topologySt);
//							proteinSubcellularLocationProperties.put(ProteinSubcellularLocationRel.TOPOLOGY_STATUS_PROPERTY, topologyStatusSt);
//							inserter.createRelationship(currentProteinId, lastLocationId, proteinSubcellularLocationRel, proteinSubcellularLocationProperties);
//
//						}
//					}
//					break;
//				case UniprotStuff.COMMENT_ALTERNATIVE_PRODUCTS_TYPE:
//					if (uniprotDataXML.getIsoforms()) {
//						List<Element> eventList = commentElem.getChildren("event");
//						List<Element> isoformList = commentElem.getChildren("isoform");
//
//						for (Element isoformElem : isoformList) {
//							String isoformIdSt = isoformElem.getChildText("id");
//							String isoformNoteSt = isoformElem.getChildText("note");
//							String isoformNameSt = isoformElem.getChildText("name");
//							String isoformSeqSt = "";
//							Element isoSeqElem = isoformElem.getChild("sequence");
//							if (isoSeqElem != null) {
//								String isoSeqTypeSt = isoSeqElem.getAttributeValue("type");
//								if (isoSeqTypeSt.equals("displayed")) {
//									isoformSeqSt = proteinSequence;
//								}
//							}
//							if (isoformNoteSt == null) {
//								isoformNoteSt = "";
//							}
//							if (isoformNameSt == null) {
//								isoformNameSt = "";
//							}
//							isoformProperties.put(IsoformNode.ID_PROPERTY, isoformIdSt);
//							isoformProperties.put(IsoformNode.NOTE_PROPERTY, isoformNoteSt);
//							isoformProperties.put(IsoformNode.NAME_PROPERTY, isoformNameSt);
//							isoformProperties.put(IsoformNode.SEQUENCE_PROPERTY, isoformSeqSt);
//							//--------------------------------------------------------
//							//long isoformId = indexService.getSingleNode(IsoformNode.ISOFORM_ID_INDEX, isoformIdSt);
//							long isoformId = -1;
//							IndexHits<Long> isoformIdIndexHits = isoformIdIndex.get(IsoformNode.ISOFORM_ID_INDEX, isoformIdSt);
//							if (isoformIdIndexHits.hasNext()) {
//								isoformId = isoformIdIndexHits.getSingle();
//							}
//							isoformIdIndexHits.close();
//							if (isoformId < 0) {
//								isoformId = createIsoformNode(isoformProperties, inserter, isoformIdIndex, nodeTypeIndex);
//							}
//
//							for (Element eventElem : eventList) {
//
//								String eventTypeSt = eventElem.getAttributeValue("type");
//								switch (eventTypeSt) {
//									case AlternativeProductInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//										inserter.createRelationship(isoformId, alternativeProductInitiationId, isoformEventGeneratorRel, null);
//										break;
//									case AlternativeProductPromoterRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//										inserter.createRelationship(isoformId, alternativeProductPromoterId, isoformEventGeneratorRel, null);
//										break;
//									case AlternativeProductRibosomalFrameshiftingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//										inserter.createRelationship(isoformId, alternativeProductRibosomalFrameshiftingId, isoformEventGeneratorRel, null);
//										break;
//									case AlternativeProductSplicingRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//										inserter.createRelationship(isoformId, alternativeProductSplicingId, isoformEventGeneratorRel, null);
//										break;
//								}
//							}
//
//							//protein isoform relationship
//							inserter.createRelationship(currentProteinId, isoformId, proteinIsoformRel, null);
//
//						}
//					}
//					break;
//				case UniprotStuff.COMMENT_SEQUENCE_CAUTION_TYPE:
//					sequenceCautionProperties.put(BasicProteinSequenceCautionRel.EVIDENCE_PROPERTY, commentEvidenceSt);
//					sequenceCautionProperties.put(BasicProteinSequenceCautionRel.STATUS_PROPERTY, commentStatusSt);
//					sequenceCautionProperties.put(BasicProteinSequenceCautionRel.TEXT_PROPERTY, commentTextSt);
//					Element conflictElem = commentElem.getChild("conflict");
//					if (conflictElem != null) {
//
//						String conflictTypeSt = conflictElem.getAttributeValue("type");
//						String resourceSt = "";
//						String idSt = "";
//						String versionSt = "";
//
//						ArrayList<String> positionsList = new ArrayList<>();
//
//						Element sequenceElem = conflictElem.getChild("sequence");
//						if (sequenceElem != null) {
//							resourceSt = sequenceElem.getAttributeValue("resource");
//							if (resourceSt == null) {
//								resourceSt = "";
//							}
//							idSt = sequenceElem.getAttributeValue("id");
//							if (idSt == null) {
//								idSt = "";
//							}
//							versionSt = sequenceElem.getAttributeValue("version");
//							if (versionSt == null) {
//								versionSt = "";
//							}
//						}
//
//						Element locationElem = commentElem.getChild("location");
//						if (locationElem != null) {
//							Element positionElem = locationElem.getChild("position");
//							if (positionElem != null) {
//								String tempPos = positionElem.getAttributeValue("position");
//								if (tempPos != null) {
//									positionsList.add(tempPos);
//								}
//							}
//						}
//
//						sequenceCautionProperties.put(BasicProteinSequenceCautionRel.RESOURCE_PROPERTY, resourceSt);
//						sequenceCautionProperties.put(BasicProteinSequenceCautionRel.ID_PROPERTY, idSt);
//						sequenceCautionProperties.put(BasicProteinSequenceCautionRel.VERSION_PROPERTY, versionSt);
//						switch (conflictTypeSt) {
//							case ProteinErroneousGeneModelPredictionRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//								if (positionsList.size() > 0) {
//									for (String tempPosition : positionsList) {
//										sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
//										inserter.createRelationship(currentProteinId, seqCautionErroneousGeneModelPredictionId, proteinErroneousGeneModelPredictionRel, sequenceCautionProperties);
//									}
//								} else {
//									sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
//									inserter.createRelationship(currentProteinId, seqCautionErroneousGeneModelPredictionId, proteinErroneousGeneModelPredictionRel, sequenceCautionProperties);
//								}
//								break;
//							case ProteinErroneousInitiationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//								if (positionsList.size() > 0) {
//									for (String tempPosition : positionsList) {
//										sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
//										inserter.createRelationship(currentProteinId, seqCautionErroneousInitiationId, proteinErroneousInitiationRel, sequenceCautionProperties);
//									}
//								} else {
//									sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
//									inserter.createRelationship(currentProteinId, seqCautionErroneousInitiationId, proteinErroneousInitiationRel, sequenceCautionProperties);
//								}
//								break;
//							case ProteinErroneousTranslationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//								if (positionsList.size() > 0) {
//									for (String tempPosition : positionsList) {
//										sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
//										inserter.createRelationship(currentProteinId, seqCautionErroneousTranslationId, proteinErroneousTranslationRel, sequenceCautionProperties);
//									}
//								} else {
//									sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
//									inserter.createRelationship(currentProteinId, seqCautionErroneousTranslationId, proteinErroneousTranslationRel, sequenceCautionProperties);
//								}
//								break;
//							case ProteinErroneousTerminationRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//								if (positionsList.size() > 0) {
//									for (String tempPosition : positionsList) {
//										sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
//										inserter.createRelationship(currentProteinId, seqCautionErroneousTerminationId, proteinErroneousTerminationRel, sequenceCautionProperties);
//									}
//								} else {
//									sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
//									inserter.createRelationship(currentProteinId, seqCautionErroneousTerminationId, proteinErroneousTerminationRel, sequenceCautionProperties);
//								}
//								break;
//							case ProteinFrameshiftRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//								if (positionsList.size() > 0) {
//									for (String tempPosition : positionsList) {
//										sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
//										inserter.createRelationship(currentProteinId, seqCautionFrameshiftId, proteinFrameshiftRel, sequenceCautionProperties);
//									}
//								} else {
//									sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
//									inserter.createRelationship(currentProteinId, seqCautionFrameshiftId, proteinFrameshiftRel, sequenceCautionProperties);
//								}
//								break;
//							case ProteinMiscellaneousDiscrepancyRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//								if (positionsList.size() > 0) {
//									for (String tempPosition : positionsList) {
//										sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, tempPosition);
//										inserter.createRelationship(currentProteinId, seqCautionMiscellaneousDiscrepancyId, proteinMiscellaneousDiscrepancyRel, sequenceCautionProperties);
//									}
//								} else {
//									sequenceCautionProperties.put(BasicProteinSequenceCautionRel.POSITION_PROPERTY, "");
//									inserter.createRelationship(currentProteinId, seqCautionMiscellaneousDiscrepancyId, proteinMiscellaneousDiscrepancyRel, sequenceCautionProperties);
//								}
//								break;
//						}
//					}
//					break;
//				case DevelopmentalStageCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, developmentalStageCommentRel, commentProperties);
//					break;
//				case MiscellaneousCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, miscellaneousCommentRel, commentProperties);
//					break;
//				case SimilarityCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, similarityCommentRel, commentProperties);
//					break;
//				case RnaEditingCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					rnaEditingCommentProperties.put(RnaEditingCommentRel.STATUS_PROPERTY, commentStatusSt);
//					rnaEditingCommentProperties.put(RnaEditingCommentRel.EVIDENCE_PROPERTY, commentEvidenceSt);
//					rnaEditingCommentProperties.put(RnaEditingCommentRel.TEXT_PROPERTY, commentTextSt);
//					List<Element> locationsList = commentElem.getChildren("location");
//					for (Element tempLoc : locationsList) {
//						String positionSt = tempLoc.getChild("position").getAttributeValue("position");
//						rnaEditingCommentProperties.put(RnaEditingCommentRel.POSITION_PROPERTY, positionSt);
//						inserter.createRelationship(currentProteinId, commentTypeId, rnaEditingCommentRel, rnaEditingCommentProperties);
//					}
//					break;
//				case PharmaceuticalCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, pharmaceuticalCommentRel, commentProperties);
//					break;
//				case EnzymeRegulationCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					inserter.createRelationship(currentProteinId, commentTypeId, enzymeRegulationCommentRel, commentProperties);
//					break;
//				case MassSpectrometryCommentRel.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//					String methodSt = commentElem.getAttributeValue("method");
//					String massSt = commentElem.getAttributeValue("mass");
//					if (methodSt == null) {
//						methodSt = "";
//					}
//					if (massSt == null) {
//						massSt = "";
//					}
//					String beginSt = "";
//					String endSt = "";
//					Element locationElem = commentElem.getChild("location");
//					if (locationElem != null) {
//						Element beginElem = commentElem.getChild("begin");
//						Element endElem = commentElem.getChild("end");
//						if (beginElem != null) {
//							beginSt = beginElem.getAttributeValue("position");
//						}
//
//						if (endElem != null) {
//							endSt = endElem.getAttributeValue("position");
//						}
//					}
//					massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.STATUS_PROPERTY, commentStatusSt);
//					massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.EVIDENCE_PROPERTY, commentEvidenceSt);
//					massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.TEXT_PROPERTY, commentTextSt);
//					massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.METHOD_PROPERTY, methodSt);
//					massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.MASS_PROPERTY, massSt);
//					massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.BEGIN_PROPERTY, beginSt);
//					massSpectrometryCommentProperties.put(MassSpectrometryCommentRel.END_PROPERTY, endSt);
//					inserter.createRelationship(currentProteinId, commentTypeId, massSpectrometryCommentRel, massSpectrometryCommentProperties);
//					break;
//			}
//
//		}
//
//
//	}

	private static void addPropertiesToProteinFeatureRelationship(TitanUniprotGraph graph, TitanProteinFeature proteinFeature,
	                    String id, String description, String evidence, String status, int begin, int end,
	                    String original, String variation, String ref){

		proteinFeature.set(graph.proteinFeatureT.description, description);
		proteinFeature.set(graph.proteinFeatureT.id, id);
		proteinFeature.set(graph.proteinFeatureT.evidence, evidence);
		proteinFeature.set(graph.proteinFeatureT.status, status);
		proteinFeature.set(graph.proteinFeatureT.begin, begin);
		proteinFeature.set(graph.proteinFeatureT.end, end);
		proteinFeature.set(graph.proteinFeatureT.original, original);
		proteinFeature.set(graph.proteinFeatureT.variation, variation);
		proteinFeature.set(graph.proteinFeatureT.ref, ref);

	}

	private static String getProteinFullName(Element proteinElement) {
		if (proteinElement == null) {
			return "";
		} else {
			Element recElem = proteinElement.getChild(PROTEIN_RECOMMENDED_NAME_TAG_NAME);
			if (recElem == null) {
				return "";
			} else {
				return recElem.getChildText(PROTEIN_FULL_NAME_TAG_NAME);
			}
		}
	}

	private static String getProteinShortName(Element proteinElement) {
		if (proteinElement == null) {
			return "";
		} else {
			Element recElem = proteinElement.getChild(PROTEIN_RECOMMENDED_NAME_TAG_NAME);
			if (recElem == null) {
				return "";
			} else {
				return recElem.getChildText(PROTEIN_SHORT_NAME_TAG_NAME);
			}
		}
	}

//	private static long createIsoformNode(Map<String, Object> isoformProperties,
//	                                      BatchInserter inserter,
//	                                      BatchInserterIndex isoformIdIndex,
//	                                      BatchInserterIndex nodeTypeIndex) {
//
//		long isoformId = inserter.createNode(isoformProperties);
//		isoformIdIndex.add(isoformId, MapUtil.map(IsoformNode.ISOFORM_ID_INDEX, isoformProperties.get(IsoformNode.ID_PROPERTY)));
//		//---adding isoform node to node_type index----
//		nodeTypeIndex.add(isoformId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, IsoformNode.NODE_TYPE));
//
//		return isoformId;
//	}
//
//	private static long createTaxonNode(Map<String, Object> taxonProperties,
//	                                    BatchInserter inserter,
//	                                    BatchInserterIndex taxonNameIndex,
//	                                    BatchInserterIndex nodeTypeIndex) {
//
//		long taxonId = inserter.createNode(taxonProperties);
//		taxonNameIndex.add(taxonId, MapUtil.map(TaxonNode.TAXON_NAME_INDEX, taxonProperties.get(TaxonNode.NAME_PROPERTY)));
//		//---adding taxon node to node_type index----
//		nodeTypeIndex.add(taxonId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, TaxonNode.NODE_TYPE));
//
//		return taxonId;
//	}
//
//	private static long createPersonNode(Map<String, Object> personProperties,
//	                                     BatchInserter inserter,
//	                                     BatchInserterIndex index,
//	                                     BatchInserterIndex nodeTypeIndex) {
//
//		long personId = inserter.createNode(personProperties);
//		index.add(personId, MapUtil.map(PersonNode.PERSON_NAME_FULL_TEXT_INDEX, personProperties.get(PersonNode.NAME_PROPERTY)));
//		//---adding person node to node_type index----
//		nodeTypeIndex.add(personId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, PersonNode.NODE_TYPE));
//
//		return personId;
//	}
//
//	private static long createConsortiumNode(Map<String, Object> consortiumProperties,
//	                                         BatchInserter inserter,
//	                                         BatchInserterIndex index,
//	                                         BatchInserterIndex nodeTypeIndex) {
//
//		long consortiumId = inserter.createNode(consortiumProperties);
//		index.add(consortiumId, MapUtil.map(ConsortiumNode.CONSORTIUM_NAME_INDEX, consortiumProperties.get(ConsortiumNode.NAME_PROPERTY)));
//		//---adding consortium node to node_type index----
//		nodeTypeIndex.add(consortiumId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, ConsortiumNode.NODE_TYPE));
//
//		return consortiumId;
//	}
//
//	private static long createInstituteNode(Map<String, Object> instituteProperties,
//	                                        BatchInserter inserter,
//	                                        BatchInserterIndex index,
//	                                        BatchInserterIndex nodeTypeIndex) {
//
//		long instituteId = inserter.createNode(instituteProperties);
//		index.add(instituteId, MapUtil.map(InstituteNode.INSTITUTE_NAME_INDEX, instituteProperties.get(InstituteNode.NAME_PROPERTY)));
//		//---adding institute node to node_type index----
//		nodeTypeIndex.add(instituteId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, InstituteNode.NODE_TYPE));
//
//		return instituteId;
//	}
//
//	private static long createCountryNode(Map<String, Object> countryProperties,
//	                                      BatchInserter inserter,
//	                                      BatchInserterIndex index,
//	                                      BatchInserterIndex nodeTypeIndex) {
//
//		long countryId = inserter.createNode(countryProperties);
//		index.add(countryId, MapUtil.map(CountryNode.COUNTRY_NAME_INDEX, countryProperties.get(CountryNode.NAME_PROPERTY)));
//		//---adding country node to node_type index----
//		nodeTypeIndex.add(countryId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, CountryNode.NODE_TYPE));
//
//		return countryId;
//	}
//
//	private static long createCityNode(Map<String, Object> cityProperties,
//	                                   BatchInserter inserter,
//	                                   BatchInserterIndex index,
//	                                   BatchInserterIndex nodeTypeIndex) {
//
//		long cityId = inserter.createNode(cityProperties);
//		index.add(cityId, MapUtil.map(CityNode.CITY_NAME_INDEX, cityProperties.get(CityNode.NAME_PROPERTY)));
//		//---adding city node to node_type index----
//		nodeTypeIndex.add(cityId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, CityNode.NODE_TYPE));
//
//		return cityId;
//	}
//
//	private static long createDbNode(Map<String, Object> dbProperties,
//	                                 BatchInserter inserter,
//	                                 BatchInserterIndex index,
//	                                 BatchInserterIndex nodeTypeIndex) {
//
//		long dbId = inserter.createNode(dbProperties);
//		index.add(dbId, MapUtil.map(DBNode.DB_NAME_INDEX, dbProperties.get(DBNode.NAME_PROPERTY)));
//		//---adding db node to node_type index----
//		nodeTypeIndex.add(dbId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, DBNode.NODE_TYPE));
//
//		return dbId;
//	}
//
//	private static long createSubcellularLocationNode(Map<String, Object> subcellularLocationProperties,
//	                                                  BatchInserter inserter,
//	                                                  BatchInserterIndex index,
//	                                                  BatchInserterIndex nodeTypeIndex) {
//
//		long subcellularLocationId = inserter.createNode(subcellularLocationProperties);
//		index.add(subcellularLocationId, MapUtil.map(SubcellularLocationNode.SUBCELLULAR_LOCATION_NAME_INDEX, subcellularLocationProperties.get(SubcellularLocationNode.NAME_PROPERTY)));
//		//---adding subcellular location node to node_type index----
//		nodeTypeIndex.add(subcellularLocationId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, SubcellularLocationNode.NODE_TYPE));
//
//		return subcellularLocationId;
//	}
//
//	private static void importProteinCitations(XMLElement entryXMLElem,
//	                                           BatchInserter inserter,
//	                                           BatchInserterIndexProvider indexProvider,
//	                                           long currentProteinId,
//	                                           UniprotDataXML uniprotDataXML) {
//
//		//-----------------create batch indexes----------------------------------
//		//----------------------------------------------------------------------
//		BatchInserterIndex personNameIndex = indexProvider.nodeIndex(PersonNode.PERSON_NAME_FULL_TEXT_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, FULL_TEXT_ST));
//		BatchInserterIndex consortiumNameIndex = indexProvider.nodeIndex(ConsortiumNode.CONSORTIUM_NAME_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex thesisTitleIndex = indexProvider.nodeIndex(ThesisNode.THESIS_TITLE_FULL_TEXT_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, FULL_TEXT_ST));
//		BatchInserterIndex instituteNameIndex = indexProvider.nodeIndex(InstituteNode.INSTITUTE_NAME_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex countryNameIndex = indexProvider.nodeIndex(CountryNode.COUNTRY_NAME_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex cityNameIndex = indexProvider.nodeIndex(CityNode.CITY_NAME_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex patentNumberIndex = indexProvider.nodeIndex(PatentNode.PATENT_NUMBER_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex bookNameIndex = indexProvider.nodeIndex(BookNode.BOOK_NAME_FULL_TEXT_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, FULL_TEXT_ST));
//		BatchInserterIndex publisherNameIndex = indexProvider.nodeIndex(PublisherNode.PUBLISHER_NAME_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex onlineArticleTitleIndex = indexProvider.nodeIndex(OnlineArticleNode.ONLINE_ARTICLE_TITLE_FULL_TEXT_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, FULL_TEXT_ST));
//		BatchInserterIndex onlineJournalNameIndex = indexProvider.nodeIndex(OnlineJournalNode.ONLINE_JOURNAL_NAME_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex submissionTitleIndex = indexProvider.nodeIndex(SubmissionNode.SUBMISSION_TITLE_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, FULL_TEXT_ST));
//		BatchInserterIndex articleTitleIndex = indexProvider.nodeIndex(ArticleNode.ARTICLE_TITLE_FULL_TEXT_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, FULL_TEXT_ST));
//		BatchInserterIndex articleDoiIdIndex = indexProvider.nodeIndex(ArticleNode.ARTICLE_DOI_ID_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex articlePubmedIdIndex = indexProvider.nodeIndex(ArticleNode.ARTICLE_PUBMED_ID_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex articleMedlineIdIndex = indexProvider.nodeIndex(ArticleNode.ARTICLE_MEDLINE_ID_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex journalNameIndex = indexProvider.nodeIndex(JournalNode.JOURNAL_NAME_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex nodeTypeIndex = indexProvider.nodeIndex(Bio4jManager.NODE_TYPE_INDEX_NAME,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		BatchInserterIndex dbNameIndex = indexProvider.nodeIndex(DBNode.DB_NAME_INDEX,
//				MapUtil.stringMap(PROVIDER_ST, LUCENE_ST, TYPE_ST, EXACT_ST));
//		//----------------------------------------------------------------------
//		//----------------------------------------------------------------------
//
//
//		List<Element> referenceList = entryXMLElem.asJDomElement().getChildren(UniprotStuff.REFERENCE_TAG_NAME);
//
//		for (Element reference : referenceList) {
//			List<Element> citationsList = reference.getChildren(UniprotStuff.CITATION_TAG_NAME);
//			for (Element citation : citationsList) {
//
//				String citationType = citation.getAttributeValue(UniprotStuff.DB_REFERENCE_TYPE_ATTRIBUTE);
//
//				List<Long> authorsPersonNodesIds = new ArrayList<>();
//				List<Long> authorsConsortiumNodesIds = new ArrayList<>();
//
//				List<Element> authorPersonElems = citation.getChild("authorList").getChildren("person");
//				List<Element> authorConsortiumElems = citation.getChild("authorList").getChildren("consortium");
//
//				for (Element person : authorPersonElems) {
//					//long personId = indexService.getSingleNode(PersonNode.PERSON_NAME_INDEX, person.getAttributeValue("name"));
//					long personId = -1;
//					IndexHits<Long> personNameIndexHits = personNameIndex.get(PersonNode.PERSON_NAME_FULL_TEXT_INDEX, person.getAttributeValue("name"));
//					if (personNameIndexHits.hasNext()) {
//						personId = personNameIndexHits.getSingle();
//					}
//					personNameIndexHits.close();
//					if (personId < 0) {
//						personProperties.put(PersonNode.NAME_PROPERTY, person.getAttributeValue("name"));
//						personId = createPersonNode(personProperties, inserter, personNameIndex, nodeTypeIndex);
//						//flushing person name index
//						personNameIndex.flush();
//					}
//					authorsPersonNodesIds.add(personId);
//				}
//
//				for (Element consortium : authorConsortiumElems) {
//
//					long consortiumId = -1;
//					IndexHits<Long> consortiumIdIndexHits = consortiumNameIndex.get(ConsortiumNode.CONSORTIUM_NAME_INDEX, consortium.getAttributeValue("name"));
//					if (consortiumIdIndexHits.hasNext()) {
//						consortiumId = consortiumIdIndexHits.getSingle();
//					}
//					consortiumIdIndexHits.close();
//					if (consortiumId < 0) {
//						consortiumProperties.put(ConsortiumNode.NAME_PROPERTY, consortium.getAttributeValue("name"));
//						consortiumId = createConsortiumNode(consortiumProperties, inserter, consortiumNameIndex, nodeTypeIndex);
//						//---flushing consortium name index--
//						consortiumNameIndex.flush();
//					}
//					authorsConsortiumNodesIds.add(consortiumId);
//				}
//				//----------------------------------------------------------------------------
//				//-----------------------------THESIS-----------------------------------------
//				switch (citationType) {
//					case ThesisNode.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//						if (uniprotDataXML.getThesis()) {
//							String dateSt = citation.getAttributeValue("date");
//							String titleSt = citation.getChildText("title");
//							if (dateSt == null) {
//								dateSt = "";
//							}
//							if (titleSt == null) {
//								titleSt = "";
//							}
//
//							long thesisId = -1;
//							IndexHits<Long> thesisTitleIndexHits = thesisTitleIndex.get(ThesisNode.THESIS_TITLE_FULL_TEXT_INDEX, titleSt);
//							if (thesisTitleIndexHits.hasNext()) {
//								thesisId = thesisTitleIndexHits.getSingle();
//							}
//							thesisTitleIndexHits.close();
//							if (thesisId < 0) {
//								thesisProperties.put(ThesisNode.DATE_PROPERTY, dateSt);
//								thesisProperties.put(ThesisNode.TITLE_PROPERTY, titleSt);
//								//---thesis node creation and indexing
//								thesisId = inserter.createNode(thesisProperties);
//								nodeTypeIndex.add(thesisId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, ThesisNode.NODE_TYPE));
//								thesisTitleIndex.add(thesisId, MapUtil.map(ThesisNode.THESIS_TITLE_FULL_TEXT_INDEX, titleSt));
//								//flushing thesis title index
//								thesisTitleIndex.flush();
//								//---authors association-----
//								for (long personId : authorsPersonNodesIds) {
//									inserter.createRelationship(thesisId, personId, thesisAuthorRel, null);
//								}
//
//								//-----------institute-----------------------------
//								String instituteSt = citation.getAttributeValue("institute");
//								String countrySt = citation.getAttributeValue("country");
//								if (instituteSt != null) {
//
//									long instituteId = -1;
//									IndexHits<Long> instituteNameIndexHits = instituteNameIndex.get(InstituteNode.INSTITUTE_NAME_INDEX, instituteSt);
//									if (instituteNameIndexHits.hasNext()) {
//										instituteId = instituteNameIndexHits.getSingle();
//									}
//									instituteNameIndexHits.close();
//									if (instituteId < 0) {
//										instituteProperties.put(InstituteNode.NAME_PROPERTY, instituteSt);
//										instituteId = createInstituteNode(instituteProperties, inserter, instituteNameIndex, nodeTypeIndex);
//										//flushing institute name index
//										instituteNameIndex.flush();
//									}
//									if (countrySt != null) {
//										//long countryId = indexService.getSingleNode(CountryNode.COUNTRY_NAME_INDEX, countrySt);
//										long countryId = -1;
//										IndexHits<Long> countryNameIndexHits = countryNameIndex.get(CountryNode.COUNTRY_NAME_INDEX, countrySt);
//										if (countryNameIndexHits.hasNext()) {
//											countryId = countryNameIndexHits.getSingle();
//										}
//										countryNameIndexHits.close();
//										if (countryId < 0) {
//											countryProperties.put(CountryNode.NAME_PROPERTY, countrySt);
//											countryId = createCountryNode(countryProperties, inserter, countryNameIndex, nodeTypeIndex);
//											//flushing country name index
//											countryNameIndex.flush();
//										}
//										inserter.createRelationship(instituteId, countryId, instituteCountryRel, null);
//									}
//									inserter.createRelationship(thesisId, instituteId, thesisInstituteRel, null);
//								}
//							}
//
//							//--protein citation relationship
//							inserter.createRelationship(thesisId, currentProteinId, thesisProteinCitationRel, null);
//
//						}
//
//						//----------------------------------------------------------------------------
//						//-----------------------------PATENT-----------------------------------------
//						break;
//					case PatentNode.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//						if (uniprotDataXML.getPatents()) {
//							String numberSt = citation.getAttributeValue("number");
//							String dateSt = citation.getAttributeValue("date");
//							String titleSt = citation.getChildText("title");
//							if (dateSt == null) {
//								dateSt = "";
//							}
//							if (titleSt == null) {
//								titleSt = "";
//							}
//							if (numberSt == null) {
//								numberSt = "";
//							}
//
//							if (!numberSt.equals("")) {
//								long patentId = -1;
//								IndexHits<Long> patentNumberIndexHits = patentNumberIndex.get(PatentNode.PATENT_NUMBER_INDEX, numberSt);
//								if (patentNumberIndexHits.hasNext()) {
//									patentId = patentNumberIndexHits.getSingle();
//								}
//								patentNumberIndexHits.close();
//
//								if (patentId < 0) {
//									patentProperties.put(PatentNode.NUMBER_PROPERTY, numberSt);
//									patentProperties.put(PatentNode.DATE_PROPERTY, dateSt);
//									patentProperties.put(PatentNode.TITLE_PROPERTY, titleSt);
//									//---patent node creation and indexing
//									patentId = inserter.createNode(patentProperties);
//									patentNumberIndex.add(patentId, MapUtil.map(PatentNode.PATENT_NUMBER_INDEX, numberSt));
//									nodeTypeIndex.add(patentId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, PatentNode.NODE_TYPE));
//									//---flushing patent number index---
//									patentNumberIndex.flush();
//									//---authors association-----
//									for (long personId : authorsPersonNodesIds) {
//										inserter.createRelationship(patentId, personId, patentAuthorRel, null);
//									}
//								}
//
//								//--protein citation relationship
//								inserter.createRelationship(patentId, currentProteinId, patentProteinCitationRel, null);
//							}
//						}
//
//						//----------------------------------------------------------------------------
//						//-----------------------------SUBMISSION-----------------------------------------
//						break;
//					case SubmissionNode.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//						if (uniprotDataXML.getSubmissions()) {
//							String dateSt = citation.getAttributeValue("date");
//							String titleSt = citation.getChildText("title");
//							String dbSt = citation.getAttributeValue("db");
//							if (dateSt == null) {
//								dateSt = "";
//							}
//							if (titleSt == null) {
//								titleSt = "";
//							}
//
//							submissionProperties.put(SubmissionNode.DATE_PROPERTY, dateSt);
//							submissionProperties.put(SubmissionNode.TITLE_PROPERTY, titleSt);
//
//							long submissionId;
//							IndexHits<Long> submissionTitleIndexHits = submissionTitleIndex.get(SubmissionNode.SUBMISSION_TITLE_INDEX, titleSt);
//							if (submissionTitleIndexHits.hasNext()) {
//								submissionId = submissionTitleIndexHits.getSingle();
//								submissionTitleIndexHits.close();
//							} else {
//								//---submission node creation and indexing
//								submissionId = inserter.createNode(submissionProperties);
//								//--indexing node by type---
//								nodeTypeIndex.add(submissionId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, SubmissionNode.NODE_TYPE));
//								if (!titleSt.isEmpty()) {
//									//--indexing node by title---
//									submissionTitleIndex.add(submissionId, MapUtil.map(SubmissionNode.SUBMISSION_TITLE_INDEX, titleSt));
//									submissionTitleIndex.flush();
//								}
//							}
//
//							//---authors association-----
//							for (long personId : authorsPersonNodesIds) {
//								inserter.createRelationship(submissionId, personId, submissionAuthorRel, null);
//							}
//							//---authors consortium association-----
//							for (long consortiumId : authorsConsortiumNodesIds) {
//								inserter.createRelationship(submissionId, consortiumId, submissionAuthorRel, null);
//							}
//
//							if (dbSt != null) {
//								long dbId = -1;
//								IndexHits<Long> dbNameIndexHits = dbNameIndex.get(DBNode.DB_NAME_INDEX, dbSt);
//								if (dbNameIndexHits.hasNext()) {
//									dbId = dbNameIndexHits.getSingle();
//								}
//								dbNameIndexHits.close();
//								if (dbId < 0) {
//									dbProperties.put(DBNode.NODE_TYPE_PROPERTY, DBNode.NODE_TYPE);
//									dbProperties.put(DBNode.NAME_PROPERTY, dbSt);
//									dbId = createDbNode(dbProperties, inserter, dbNameIndex, nodeTypeIndex);
//									dbNameIndex.flush();
//								}
//								//-----submission db relationship-----
//								inserter.createRelationship(submissionId, dbId, submissionDbRel, null);
//							}
//
//							//--protein citation relationship
//							inserter.createRelationship(submissionId, currentProteinId, submissionProteinCitationRel, null);
//
//						}
//
//						//----------------------------------------------------------------------------
//						//-----------------------------BOOK-----------------------------------------
//						break;
//					case BookNode.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//						if (uniprotDataXML.getBooks()) {
//							String nameSt = citation.getAttributeValue("name");
//							String dateSt = citation.getAttributeValue("date");
//							String titleSt = citation.getChildText("title");
//							String publisherSt = citation.getAttributeValue("publisher");
//							String firstSt = citation.getAttributeValue("first");
//							String lastSt = citation.getAttributeValue("last");
//							String citySt = citation.getAttributeValue("city");
//							String volumeSt = citation.getAttributeValue("volume");
//							if (nameSt == null) {
//								nameSt = "";
//							}
//							if (dateSt == null) {
//								dateSt = "";
//							}
//							if (titleSt == null) {
//								titleSt = "";
//							}
//							if (publisherSt == null) {
//								publisherSt = "";
//							}
//							if (firstSt == null) {
//								firstSt = "";
//							}
//							if (lastSt == null) {
//								lastSt = "";
//							}
//							if (citySt == null) {
//								citySt = "";
//							}
//							if (volumeSt == null) {
//								volumeSt = "";
//							}
//
//							long bookId = -1;
//							IndexHits<Long> bookNameIndexHits = bookNameIndex.get(BookNode.BOOK_NAME_FULL_TEXT_INDEX, nameSt);
//							if (bookNameIndexHits.hasNext()) {
//								bookId = bookNameIndexHits.getSingle();
//							}
//							bookNameIndexHits.close();
//
//							if (bookId < 0) {
//								bookProperties.put(BookNode.NAME_PROPERTY, nameSt);
//								bookProperties.put(BookNode.DATE_PROPERTY, dateSt);
//								//---book node creation and indexing
//								bookId = inserter.createNode(bookProperties);
//
//								bookNameIndex.add(bookId, MapUtil.map(BookNode.BOOK_NAME_FULL_TEXT_INDEX, nameSt));
//								//--indexing node by type---
//								nodeTypeIndex.add(bookId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, BookNode.NODE_TYPE));
//
//								//--flushing book name index---
//								bookNameIndex.flush();
//								//---authors association-----
//								for (long personId : authorsPersonNodesIds) {
//									inserter.createRelationship(bookId, personId, bookAuthorRel, null);
//								}
//
//								//---editor association-----
//								Element editorListElem = citation.getChild("editorList");
//								if (editorListElem != null) {
//									List<Element> editorsElems = editorListElem.getChildren("person");
//									for (Element person : editorsElems) {
//										//long editorId = indexService.getSingleNode(PersonNode.PERSON_NAME_INDEX, person.getAttributeValue("name"));
//										long editorId = -1;
//										IndexHits<Long> personNameIndexHits = personNameIndex.get(PersonNode.PERSON_NAME_FULL_TEXT_INDEX, person.getAttributeValue("name"));
//										if (personNameIndexHits.hasNext()) {
//											editorId = personNameIndexHits.getSingle();
//										}
//										personNameIndexHits.close();
//										if (editorId < 0) {
//											personProperties.put(PersonNode.NAME_PROPERTY, person.getAttributeValue("name"));
//											editorId = createPersonNode(personProperties, inserter, personNameIndex, nodeTypeIndex);
//										}
//										//---flushing person name index---
//										personNameIndex.flush();
//										//editor association
//										inserter.createRelationship(bookId, editorId, bookEditorRel, null);
//									}
//								}
//
//
//								//----publisher--
//								if (!publisherSt.equals("")) {
//									//long publisherId = indexService.getSingleNode(PublisherNode.PUBLISHER_NAME_INDEX, publisherSt);
//									long publisherId = -1;
//									IndexHits<Long> publisherNameIndexHits = publisherNameIndex.get(PublisherNode.PUBLISHER_NAME_INDEX, publisherSt);
//									if (publisherNameIndexHits.hasNext()) {
//										publisherId = publisherNameIndexHits.getSingle();
//									}
//									publisherNameIndexHits.close();
//									if (publisherId < 0) {
//										publisherProperties.put(PublisherNode.NAME_PROPERTY, publisherSt);
//										publisherId = inserter.createNode(publisherProperties);
//										//--indexing node by type---
//										nodeTypeIndex.add(publisherId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, PublisherNode.NODE_TYPE));
//										publisherNameIndex.add(publisherId, MapUtil.map(PublisherNode.PUBLISHER_NAME_INDEX, publisherSt));
//										//--flushing publisher name index--
//										publisherNameIndex.flush();
//									}
//									inserter.createRelationship(bookId, publisherId, bookPublisherRel, null);
//								}
//
//								//-----city-----
//								if (!citySt.equals("")) {
//									//long cityId = indexService.getSingleNode(CityNode.CITY_NAME_INDEX, citySt);
//									long cityId = -1;
//									IndexHits<Long> cityNameIndexHits = cityNameIndex.get(CityNode.CITY_NAME_INDEX, citySt);
//									if (cityNameIndexHits.hasNext()) {
//										cityId = cityNameIndexHits.getSingle();
//									}
//									cityNameIndexHits.close();
//									if (cityId < 0) {
//										cityProperties.put(CityNode.NAME_PROPERTY, citySt);
//										cityId = createCityNode(cityProperties, inserter, cityNameIndex, nodeTypeIndex);
//										//-----flushing city name index---
//										cityNameIndex.flush();
//									}
//									inserter.createRelationship(bookId, cityId, bookCityRel, null);
//								}
//							}
//
//							bookProteinCitationProperties.put(BookProteinCitationRel.FIRST_PROPERTY, firstSt);
//							bookProteinCitationProperties.put(BookProteinCitationRel.LAST_PROPERTY, lastSt);
//							bookProteinCitationProperties.put(BookProteinCitationRel.VOLUME_PROPERTY, volumeSt);
//							bookProteinCitationProperties.put(BookProteinCitationRel.TITLE_PROPERTY, titleSt);
//							//--protein citation relationship
//							inserter.createRelationship(bookId, currentProteinId, bookProteinCitationRel, bookProteinCitationProperties);
//
//						}
//
//						//----------------------------------------------------------------------------
//						//-----------------------------ONLINE ARTICLE-----------------------------------------
//						break;
//					case OnlineArticleNode.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//						if (uniprotDataXML.getOnlineArticles()) {
//							String locatorSt = citation.getChildText("locator");
//							String nameSt = citation.getAttributeValue("name");
//							String titleSt = citation.getChildText("title");
//
//							if (titleSt == null) {
//								titleSt = "";
//							}
//							if (nameSt == null) {
//								nameSt = "";
//							}
//							if (locatorSt == null) {
//								locatorSt = "";
//							}
//
//							long onlineArticleId = -1;
//							IndexHits<Long> onlineArticleTitleIndexHits = onlineArticleTitleIndex.get(OnlineArticleNode.ONLINE_ARTICLE_TITLE_FULL_TEXT_INDEX, titleSt);
//							if (onlineArticleTitleIndexHits.hasNext()) {
//								onlineArticleId = onlineArticleTitleIndexHits.getSingle();
//							}
//							onlineArticleTitleIndexHits.close();
//							if (onlineArticleId < 0) {
//								onlineArticleProperties.put(OnlineArticleNode.TITLE_PROPERTY, titleSt);
//								onlineArticleId = inserter.createNode(onlineArticleProperties);
//								//--indexing node by type---
//								nodeTypeIndex.add(onlineArticleId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, OnlineArticleNode.NODE_TYPE));
//
//								if (!titleSt.equals("")) {
//									onlineArticleTitleIndex.add(onlineArticleId, MapUtil.map(OnlineArticleNode.ONLINE_ARTICLE_TITLE_FULL_TEXT_INDEX, titleSt));
//									//-----flushing online article title index---
//									onlineArticleTitleIndex.flush();
//								}
//
//								//---authors person association-----
//								for (long personId : authorsPersonNodesIds) {
//									inserter.createRelationship(onlineArticleId, personId, onlineArticleAuthorRel, null);
//								}
//								//---authors consortium association-----
//								for (long consortiumId : authorsConsortiumNodesIds) {
//									inserter.createRelationship(onlineArticleId, consortiumId, onlineArticleAuthorRel, null);
//								}
//
//								//------online journal-----------
//								if (!nameSt.equals("")) {
//
//									long onlineJournalId = -1;
//									IndexHits<Long> onlineJournalNameIndexHits = onlineJournalNameIndex.get(OnlineJournalNode.ONLINE_JOURNAL_NAME_INDEX, nameSt);
//									if (onlineJournalNameIndexHits.hasNext()) {
//										onlineJournalId = onlineJournalNameIndexHits.getSingle();
//									}
//									onlineJournalNameIndexHits.close();
//									if (onlineJournalId < 0) {
//										onlineJournalProperties.put(OnlineJournalNode.NAME_PROPERTY, nameSt);
//										onlineJournalId = inserter.createNode(onlineJournalProperties);
//										//--indexing node by type---
//										nodeTypeIndex.add(onlineJournalId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, OnlineJournalNode.NODE_TYPE));
//										onlineJournalNameIndex.add(onlineJournalId, MapUtil.map(OnlineJournalNode.ONLINE_JOURNAL_NAME_INDEX, nameSt));
//
//										//---flushing online journal name index---
//										onlineJournalNameIndex.flush();
//									}
//
//									onlineArticleJournalProperties.put(OnlineArticleJournalRel.LOCATOR_PROPERTY, locatorSt);
//									inserter.createRelationship(onlineArticleId, onlineJournalId, onlineArticleJournalRel, onlineArticleJournalProperties);
//								}
//								//----------------------------
//							}
//							//protein citation
//							inserter.createRelationship(onlineArticleId, currentProteinId, onlineArticleProteinCitationRel, null);
//
//						}
//
//						//----------------------------------------------------------------------------
//						//-----------------------------ARTICLE-----------------------------------------
//						break;
//					case ArticleNode.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//						if (uniprotDataXML.getArticles()) {
//							String journalNameSt = citation.getAttributeValue("name");
//							String dateSt = citation.getAttributeValue("date");
//							String titleSt = citation.getChildText("title");
//							String firstSt = citation.getAttributeValue("first");
//							String lastSt = citation.getAttributeValue("last");
//							String volumeSt = citation.getAttributeValue("volume");
//							String doiSt = "";
//							String medlineSt = "";
//							String pubmedSt = "";
//
//							if (journalNameSt == null) {
//								journalNameSt = "";
//							}
//							if (dateSt == null) {
//								dateSt = "";
//							}
//							if (firstSt == null) {
//								firstSt = "";
//							}
//							if (lastSt == null) {
//								lastSt = "";
//							}
//							if (volumeSt == null) {
//								volumeSt = "";
//							}
//							if (titleSt == null) {
//								titleSt = "";
//							}
//
//							List<Element> dbReferences = citation.getChildren("dbReference");
//							for (Element tempDbRef : dbReferences) {
//								switch (tempDbRef.getAttributeValue("type")) {
//									case "DOI":
//										doiSt = tempDbRef.getAttributeValue("id");
//										break;
//									case "MEDLINE":
//										medlineSt = tempDbRef.getAttributeValue("id");
//										break;
//									case "PubMed":
//										pubmedSt = tempDbRef.getAttributeValue("id");
//										break;
//								}
//							}
//
//							//long articleId = indexService.getSingleNode(ArticleNode.ARTICLE_TITLE_FULL_TEXT_INDEX, titleSt);
//							long articleId = -1;
//							IndexHits<Long> articleTitleIndexHits = articleTitleIndex.get(ArticleNode.ARTICLE_TITLE_FULL_TEXT_INDEX, titleSt);
//							if (articleTitleIndexHits.hasNext()) {
//								articleId = articleTitleIndexHits.getSingle();
//							}
//							articleTitleIndexHits.close();
//							if (articleId < 0) {
//								articleProperties.put(ArticleNode.TITLE_PROPERTY, titleSt);
//								articleProperties.put(ArticleNode.DOI_ID_PROPERTY, doiSt);
//								articleProperties.put(ArticleNode.MEDLINE_ID_PROPERTY, medlineSt);
//								articleProperties.put(ArticleNode.PUBMED_ID_PROPERTY, pubmedSt);
//								articleId = inserter.createNode(articleProperties);
//								//--indexing node by type---
//								nodeTypeIndex.add(articleId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, ArticleNode.NODE_TYPE));
//								if (!titleSt.equals("")) {
//									articleTitleIndex.add(articleId, MapUtil.map(ArticleNode.ARTICLE_TITLE_FULL_TEXT_INDEX, titleSt));
//									//--flushing article title index---
//									articleTitleIndex.flush();
//								}
//
//								//---indexing by medline, doi and pubmed--
//								if (!doiSt.isEmpty()) {
//									articleDoiIdIndex.add(articleId, MapUtil.map(ArticleNode.ARTICLE_DOI_ID_INDEX, doiSt));
//								}
//								if (!medlineSt.isEmpty()) {
//									articleMedlineIdIndex.add(articleId, MapUtil.map(ArticleNode.ARTICLE_MEDLINE_ID_INDEX, medlineSt));
//								}
//								if (!pubmedSt.isEmpty()) {
//									articlePubmedIdIndex.add(articleId, MapUtil.map(ArticleNode.ARTICLE_PUBMED_ID_INDEX, pubmedSt));
//								}
//
//								//---authors person association-----
//								for (long personId : authorsPersonNodesIds) {
//									inserter.createRelationship(articleId, personId, articleAuthorRel, null);
//								}
//								//---authors consortium association-----
//								for (long consortiumId : authorsConsortiumNodesIds) {
//									inserter.createRelationship(articleId, consortiumId, articleAuthorRel, null);
//								}
//
//								//------journal-----------
//								if (!journalNameSt.equals("")) {
//									//long journalId = indexService.getSingleNode(JournalNode.JOURNAL_NAME_INDEX, journalNameSt);
//									long journalId = -1;
//									IndexHits<Long> journalNameIndexHits = journalNameIndex.get(JournalNode.JOURNAL_NAME_INDEX, journalNameSt);
//									if (journalNameIndexHits.hasNext()) {
//										journalId = journalNameIndexHits.getSingle();
//									}
//									journalNameIndexHits.close();
//									if (journalId < 0) {
//										journalProperties.put(JournalNode.NAME_PROPERTY, journalNameSt);
//										journalId = inserter.createNode(journalProperties);
//										//--indexing node by type---
//										nodeTypeIndex.add(journalId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, JournalNode.NODE_TYPE));
//										journalNameIndex.add(journalId, MapUtil.map(JournalNode.JOURNAL_NAME_INDEX, journalNameSt));
//										//----flushing journal name index----
//										journalNameIndex.flush();
//									}
//
//									articleJournalProperties.put(ArticleJournalRel.DATE_PROPERTY, dateSt);
//									articleJournalProperties.put(ArticleJournalRel.FIRST_PROPERTY, firstSt);
//									articleJournalProperties.put(ArticleJournalRel.LAST_PROPERTY, lastSt);
//									articleJournalProperties.put(ArticleJournalRel.VOLUME_PROPERTY, volumeSt);
//									inserter.createRelationship(articleId, journalId, articleJournalRel, articleJournalProperties);
//								}
//								//----------------------------
//							}
//							//protein citation
//							inserter.createRelationship(articleId, currentProteinId, articleProteinCitationRel, null);
//
//						}
//
//						//----------------------------------------------------------------------------
//						//----------------------UNPUBLISHED OBSERVATIONS-----------------------------------------
//						break;
//					case UnpublishedObservationNode.UNIPROT_ATTRIBUTE_TYPE_VALUE:
//						if (uniprotDataXML.getUnpublishedObservations()) {
//							String dateSt = citation.getAttributeValue("date");
//							if (dateSt == null) {
//								dateSt = "";
//							}
//
//							unpublishedObservationProperties.put(UnpublishedObservationNode.DATE_PROPERTY, dateSt);
//							long unpublishedObservationId = inserter.createNode(unpublishedObservationProperties);
//							//--indexing node by type---
//							nodeTypeIndex.add(unpublishedObservationId, MapUtil.map(Bio4jManager.NODE_TYPE_INDEX_NAME, UnpublishedObservationNode.NODE_TYPE));
//
//							//---authors person association-----
//							for (long personId : authorsPersonNodesIds) {
//								inserter.createRelationship(unpublishedObservationId, personId, unpublishedObservationAuthorRel, null);
//							}
//
//							inserter.createRelationship(unpublishedObservationId, currentProteinId, unpublishedObservationProteinCitationRel, null);
//						}
//						break;
//				}
//			}
//		}
//
//
//	}

	private static String[] convertToStringArray(List<String> list) {
		String[] result = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}
}