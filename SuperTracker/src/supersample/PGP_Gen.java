package supersample;

import org.jgap.BaseGene;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.UnsupportedRepresentationException;

public class PGP_Gen extends BaseGene {

	public PGP_Gen(Configuration a_configuration) throws InvalidConfigurationException {
		super(a_configuration);
		// TODO Auto-generated constructor stub
	}
	public PGP_Gen(Configuration a_configuration, double max, double min) throws InvalidConfigurationException {
		super(a_configuration);
		
		if( min < 0 || max < 0 )
        {
            throw new IllegalArgumentException(
                "The upper bound and the lower bound must be non-negative." );
        }

        
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyMutation(int index, double a_percentage) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPersistentRepresentation() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAllele(Object a_newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setToRandomValue(RandomGenerator a_numberGenerator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueFromPersistentRepresentation(String a_representation)
			throws UnsupportedOperationException, UnsupportedRepresentationException {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Object getInternalValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Gene newGeneInternal() {
		// TODO Auto-generated method stub
		return null;
	}

}
