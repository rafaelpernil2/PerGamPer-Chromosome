package supersample;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.jgap.*;
import org.jgap.impl.DoubleGene;
import org.jgap.xml.GeneCreationException;

@SuppressWarnings("serial")
public class Chromosome implements IChromosome {

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genes;
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	private DoubleGene[] genes;
	private DoubleGene distance_limit;
	private DoubleGene speed_change_probability;
	private DoubleGene range_of_speeds;
	private DoubleGene min_robot_speed;
	public Chromosome (DoubleGene[] a_initial_genes) throws InvalidConfigurationException {
		genes = a_initial_genes;
		distance_limit= a_initial_genes[0];
		speed_change_probability = a_initial_genes[1];
		range_of_speeds = a_initial_genes[0];
		min_robot_speed = a_initial_genes[0];
	}
	
	@Override
	public synchronized DoubleGene getGene(int a_desiredLocus) {
		// TODO Auto-generated method stub
		return genes[a_desiredLocus];
	}
	@Override
	public synchronized DoubleGene[] getGenes() {
		// TODO Auto-generated method stub
		return genes;
	}
	
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return super.equals(other);
	}
	
	@Override
	public int compareTo(Object other) {
		// TODO Auto-generated method stub
		return compareTo(other);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public String getUniqueID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUniqueIDTemplate(int a_index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUniqueIDTemplate(String a_templateID, int a_index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getApplicationData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getFitnessValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getFitnessValueDirectly() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void increaseAge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void increaseOperatedOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSelectedForNextGeneration() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int operatedOn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resetAge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetOperatedOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAge(int a_age) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setApplicationData(Object a_newData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConstraintChecker(IGeneConstraintChecker a_constraintChecker) throws InvalidConfigurationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFitnessValue(double a_newFitnessValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFitnessValueDirectly(double a_newFitnessValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGenes(Gene[] a_genes) throws InvalidConfigurationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsSelectedForNextGeneration(boolean a_isSelected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
