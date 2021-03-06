package com.cloud.ocs.schedule.vm.ga;

import java.util.List;
import java.util.Random;

/**
 * 用于执行遗传算法的类
 * 
 * @author Wang Chao
 *
 * @date 2015-3-5 下午7:22:11
 *
 */
public class GAExecutor {
	
	public final static int POPULATION_NUM = 50; //种群数量
	public final static int MAX_GEN = 1000; //最大遗传代数
//	public final static double MIN_TERMINAL_FITNESS = 2.0;
	public final static double LOAD_BALANCING_THRESHOLD = 0.5; //负载均衡约束条件
	public final static double REPLICATION_PROBABILITY = 0.1; //选择概率Pr
	public final static double HYBRIDIZATION_PROBABILITY = 0.9; //交叉概率Pc
	
	public final static int DEF_NUM = 5; //预定义种群中达到负载均衡的个体数
	
	public final static double FITNESS_FUNCTION_COEF_A = 0.5;
	public final static double FITNESS_FUNCTION_COEF_B = 1.5;
	
	public Individual executeGA(int pmNum, int vmNum, double[] vmsLoad, Individual originIndividual) {
		int curGen = 0; //当前种群的代数
		Population population = initializePopulation(pmNum, vmNum, vmsLoad);
		
		//计算population中的适应度
		population.computeFitnessInPopulation();
		while (true) {
			if (population.getLoadBalancingIndividualNum() >= DEF_NUM || curGen >= MAX_GEN) {
				break;
			}
			List<Individual> selectedIndividuals = population.selectInPopulation();
			List<Individual> crossedIndividuals = population.crossInPopulation();
			selectedIndividuals.addAll(crossedIndividuals);
			
			Population newPopulation = new Population();
			newPopulation.setIndividuals(selectedIndividuals);
			newPopulation.mutateInPopulation(curGen, vmNum, pmNum);
			population = newPopulation;
			
			//计算population中的适应度
			population.computeFitnessInPopulation();
			
			curGen++;
		}
		
		Individual optimalIndividual = null;
		if (population.getLoadBalancingIndividualNum() >= DEF_NUM) { //选择迁移开销小的
			optimalIndividual = population.getMinMigrateCostIndividual(originIndividual);
			System.out.println("1");
		}
		else {
			optimalIndividual = population.getMaxFitnessIndividualInPopulation(); //选择适应度最大的
			System.out.println("2");
		}
		
		System.out.println("-------------------------------");
		optimalIndividual.printChromosome();
		System.out.println(optimalIndividual.getFitness());
		for (int i = 0; i < pmNum; i++) {
			System.out.print(optimalIndividual.getPmsLoad()[i] + "  ");
		}
		System.out.println();
		System.out.println("*****" + curGen + "*****");
		
		return optimalIndividual;
	}
	
	public Population initializePopulation(int pmNum, int vmNum, double[] vmsLoad) {
		Population population = new Population();
		int curIndividualNum = 0;
		
		while (curIndividualNum < POPULATION_NUM) {
			Individual one = new Individual(pmNum, vmNum, vmsLoad);
			//先采用随机方法
			Random random = new Random();
			for (int i = 0; i < vmNum; i++) {
				int randomPmNum = random.nextInt(pmNum);
				one.addVM(randomPmNum, i);
			}
//			one.addVM(0, 0);
//			one.addVM(0, 1);
//			one.addVM(0, 2);
//			one.addVM(0, 3);
//			one.addVM(1, 4);
//			one.addVM(1, 5);
//			one.addVM(1, 6);
//			one.addVM(2, 7);
//			one.addVM(2, 8);
//			one.addVM(3, 9);
//			one.addVM(3, 10);
//			one.addVM(3, 11);
//			one.addVM(3, 12);
//			one.addVM(4, 13);
//			one.addVM(4, 14);
//			one.addVM(4, 15);
			
//			one.addVM(0, 0);
//			one.addVM(0, 1);
//			one.addVM(0, 9);
//			one.addVM(1, 2);
//			one.addVM(1, 4);
//			one.addVM(1, 5);
//			one.addVM(1, 10);
//			one.addVM(2, 6);
//			one.addVM(2, 7);
//			one.addVM(2, 11);
//			one.addVM(3, 12);
//			one.addVM(3, 13);
//			one.addVM(3, 14);
//			one.addVM(4, 3);
//			one.addVM(4, 8);
//			one.addVM(4, 15);
			one.computePMsLoad();
			one.computeFitness();
			one.computePMsLoadStandardDeviation();
//			one.printChromosome();
//			System.out.println(one.computePMsLoadExpectation());
//			System.out.println(one.computePMsLoadVariation());
//			System.out.println(one.getFitness());
//			System.out.println("**************");
			population.addIndividual(one);
			curIndividualNum++;
		}
		
//		Individual one = new Individual(pmNum, vmNum, vmsLoad);
//		one.addVM(0, 0);
//		one.addVM(0, 1);
//		one.addVM(0, 9);
//		one.addVM(1, 2);
//		one.addVM(1, 4);
//		one.addVM(1, 5);
//		one.addVM(1, 10);
//		one.addVM(2, 6);
//		one.addVM(2, 7);
//		one.addVM(2, 11);
//		one.addVM(3, 12);
//		one.addVM(3, 13);
//		one.addVM(3, 14);
//		one.addVM(4, 3);
//		one.addVM(4, 8);
//		one.addVM(4, 15);
//		one.computePMsLoad();
//		one.computeFitness();
//		population.getIndividuals().remove(49);
//		population.addIndividual(one);
		
		return population;
	}
	
}
