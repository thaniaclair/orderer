package br.com.oncast.helper;

import java.util.LinkedList;
import java.util.List;

import br.com.oncast.model.Rule;

/**
 * This class is a client's helper. We need to split the rules string from the 
 * properties file and create a list of rules to handle the order rules in a 
 * better way. 
 * 
 * @author thania
 *
 */
public class RuleParser 
{

	/**
	 * Get from the properties the order rules and returns a list of Rule objects.
	 * 
	 * @return A list of order rules.
	 */
	public static List<Rule> getRulesFromFile() 
	{
		List<Rule> rulesList = new LinkedList<Rule>();
		String allRules = ConfigProperties.getString("rules");
		String[] rules = allRules.split(";");
		// It's not supposed to happen, but it'd better to avoid.
		if (rules == null) 
		{
			return null;
		}
		// Add the rules to the list in the inverse order.
		for (int i = rules.length - 1; i >= 0; i--) 
		{
			String rule = rules[i];
			// A rule consists on an attribute and a direction. It's separated
			// by space.
			String[] ruleParts = rule.split(" ");
			String attribute = ruleParts[0];
			String direction = ruleParts[1];
			// Create the Rule object and add in the return list.
			Rule ruleObj = new Rule();
			ruleObj.setAttribute(attribute);
			ruleObj.setDirection(direction);
			rulesList.add(ruleObj);
		}
		return rulesList;
	}
	
}

